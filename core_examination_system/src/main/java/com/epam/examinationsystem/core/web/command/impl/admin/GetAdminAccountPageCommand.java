package com.epam.examinationsystem.core.web.command.impl.admin;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PleaseService
public class GetAdminAccountPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAdminAccountPageCommand.class);

    @PleaseInject
    private UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.ADMIN_ACCOUNT_PAGE);
        return new CommandResult(Path.ADMIN_ACCOUNT_PAGE);
    }
}
