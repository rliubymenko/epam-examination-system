package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PleaseService
public class GetRegistrationPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetRegistrationPageCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.REGISTRATION_PAGE);
        return new CommandResult(Path.REGISTRATION_PAGE);
    }
}
