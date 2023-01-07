package com.epam.examinationsystem.core.web.command.impl;

import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EmptyCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Redirect to home page: {}", Path.HOME);
        return new CommandResult(Path.HOME, true);
    }
}
