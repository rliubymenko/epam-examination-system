package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@PleaseService
public class DeleteQuestionPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteQuestionPageCommand.class);

    @PleaseInject
    private QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        LOG.debug("Deleting question by uuid {}", uuid);
        if (ParameterValidator.isValidUUID(uuid)) {
            try {
                questionService.deleteByUuid(UUID.fromString(uuid));
                return new CommandResult(Path.QUESTIONS, true);
            } catch (ServiceException e) {
                LOG.error("Error during deleting question has been occurred {}", e.getMessage());
                return new CommandResult(Path.QUESTIONS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
