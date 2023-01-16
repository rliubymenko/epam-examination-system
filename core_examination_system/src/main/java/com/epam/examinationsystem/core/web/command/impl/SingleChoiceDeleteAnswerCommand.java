package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
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
public class SingleChoiceDeleteAnswerCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(SingleChoiceDeleteAnswerCommand.class);

    @PleaseInject
    private AnswerService answerService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        String newTrueAnswerString = request.getParameter(Parameter.NEW_TRUE_ANSWER_UUID);
        LOG.debug("Deletion single choice answer and set new true answer");
        if (ParameterValidator.isValidUUID(uuid) && ParameterValidator.isValidUUID(newTrueAnswerString)) {
            try {
                UUID newTrueAnswerUuid = UUID.fromString(newTrueAnswerString);
                UUID answerUuid = UUID.fromString(uuid);
                answerService.deleteByUuidAndSetNewTrueAnswer(answerUuid, newTrueAnswerUuid);
                return new CommandResult(Path.ANSWERS, true);
            } catch (ServiceException e) {
                LOG.error("Error during deleting answer and setting new true answer has been occurred {}", e.getMessage());
                return new CommandResult(Path.ANSWERS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
