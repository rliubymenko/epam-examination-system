package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@PleaseService
public class EditAnswerCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditAnswerCommand.class);

    @PleaseInject
    private AnswerService answerService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uuid = request.getParameter(Parameter.UUID);
            String newTrueAnswerUuidString = request.getParameter(Parameter.NEW_TRUE_ANSWER_UUID);

            if (ParameterValidator.isValidUUID(uuid) && answerService.existsByUuid(UUID.fromString(uuid))) {
                AnswerDto currentAnswer = answerService.findByUuid(UUID.fromString(uuid)).get();

                String content = request.getParameter(Parameter.CONTENT);
                String isCorrect = request.getParameter(Parameter.TRUE_FALSE_ANSWER);
                HttpSession session = request.getSession();

                if (StringUtils.isNotBlank(newTrueAnswerUuidString)) {
                    AnswerDto toUpdate = (AnswerDto) session.getAttribute(Attribute.ANSWER);
                    answerService.updateAnswerAndSetNewTrueAnswer(toUpdate, UUID.fromString(newTrueAnswerUuidString));
                    session.removeAttribute(Attribute.ANSWER);
                    session.removeAttribute(Attribute.ANSWERS);
                    return new CommandResult(Path.ANSWERS, true);
                }

                Set<String> inconsistencies = performValidation(content);

                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid answer data");
                    request.setAttribute(Attribute.ANSWER, currentAnswer);
                    request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                    return new CommandResult(Path.EDIT_ANSWERS_PAGE);
                }

                AnswerDto answer = AnswerDto.builder()
                        .setUuid(uuid)
                        .setContent(content)
                        .setIsCorrect(isCorrect)
                        .setQuestion(currentAnswer.getQuestion())
                        .build();

                if (answerService.update(answer)) {
                    LOG.debug("The answer {} has been updated successfully", answer);
                    return new CommandResult(Path.ANSWERS, true);
                } else {
                    UUID questionUuid = UUID.fromString(currentAnswer.getQuestion().getUuid());
                    List<AnswerDto> answersForQuestion = answerService.findAllByQuestionUuid(questionUuid)
                            .stream()
                            .filter(matchedAnswer -> !matchedAnswer.getUuid().equals(uuid))
                            .toList();
                    if (answersForQuestion.isEmpty()) {
                        request.setAttribute(Parameter.ERROR_CHANGE_SINGLE_CHOICE_TRUE_ANSWER, true);
                    }
                    session.setAttribute(Parameter.ANSWER, answer);
                    session.setAttribute(Attribute.ANSWERS, answersForQuestion);
                    return new CommandResult(Path.EDIT_ANSWERS_PAGE);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during creating answer has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_ANSWERS_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    private Set<String> performValidation(String content) {
        Set<String> inconsistencies = new HashSet<>();
        if (StringUtils.isBlank(content)) {
            inconsistencies.add("content");
        }
        return inconsistencies;
    }
}
