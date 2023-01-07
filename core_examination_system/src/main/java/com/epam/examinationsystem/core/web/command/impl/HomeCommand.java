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
public class HomeCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(HomeCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Go to home page: {}", Path.HOME);
        return new CommandResult(Path.HOME_PAGE);
    }
}
