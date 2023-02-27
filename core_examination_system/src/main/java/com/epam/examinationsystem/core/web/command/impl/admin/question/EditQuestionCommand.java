package com.epam.examinationsystem.core.web.command.impl.admin.question;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@PleaseService
public class EditQuestionCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditQuestionCommand.class);

    @PleaseInject
    private QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uuid = request.getParameter(Parameter.UUID);
            LOG.debug("Edit test with uuid: {}", uuid);
            if (ParameterValidator.isValidUUID(uuid) && questionService.existsByUuid(UUID.fromString(uuid))) {
                QuestionDto currentQuestion = questionService.findByUuid(UUID.fromString(uuid)).get();

                String content = request.getParameter(Parameter.CONTENT);
                String description = request.getParameter(Parameter.DESCRIPTION);
                Set<String> inconsistencies = performValidation(content);

                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid question data");
                    request.setAttribute(Attribute.QUESTION, currentQuestion);
                    request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                    return new CommandResult(Path.EDIT_QUESTION_PAGE);
                }

                QuestionDto question = QuestionDto.builder()
                        .setUuid(uuid)
                        .setContent(content)
                        .setDescription(description)
                        .setTest(new QuestionDto.TestForQuestion(currentQuestion.getUuid(), null))
                        .build();

                if (questionService.update(question)) {
                    LOG.debug("The question {} has been updated successfully", question);
                    return new CommandResult(Path.QUESTIONS, true);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during creating question has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_QUESTION_PAGE);
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
