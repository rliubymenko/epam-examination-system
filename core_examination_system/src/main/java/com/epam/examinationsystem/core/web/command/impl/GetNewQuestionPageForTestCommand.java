package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PleaseService
public class GetNewQuestionPageForTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetNewQuestionPageForTestCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.NEW_QUESTION_PAGE);
        String testUuid = request.getParameter(Parameter.TEST_UUID);
        request.setAttribute(Attribute.TEST_UUID, testUuid);
        return new CommandResult(Path.NEW_QUESTION_PAGE);
    }
}
