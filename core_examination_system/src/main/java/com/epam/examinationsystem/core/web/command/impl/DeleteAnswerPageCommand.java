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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@PleaseService
public class DeleteAnswerPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteAnswerPageCommand.class);

    @PleaseInject
    private AnswerService answerService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        String questionUuidString = request.getParameter(Parameter.QUESTION_UUID);
        LOG.debug("Deleting answer by uuid {}", uuid);
        if (ParameterValidator.isValidUUID(uuid) && ParameterValidator.isValidUUID(questionUuidString)) {
            try {
                UUID answerUuid = UUID.fromString(uuid);
                UUID questionUuid = UUID.fromString(questionUuidString);
                if (!answerService.deleteByUuidAndQuestionUuid(answerUuid, questionUuid)) {
                    List<AnswerDto> answersForQuestion = answerService.findAllByQuestionUuid(questionUuid)
                            .stream()
                            .filter(matchedAnswer -> !matchedAnswer.getUuid().equals(uuid))
                            .toList();
                    request.setAttribute(Parameter.ANSWER_UUID, uuid);
                    request.setAttribute(Attribute.ANSWERS, answersForQuestion);
                    return new CommandResult(Path.SINGLE_CHOICE_DELETE_ANSWER_PAGE);
                }
                return new CommandResult(Path.ANSWERS, true);
            } catch (ServiceException e) {
                LOG.error("Error during deleting answer has been occurred {}", e.getMessage());
                return new CommandResult(Path.ANSWERS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
