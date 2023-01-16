package com.epam.examinationsystem.core.web.command.impl.auth;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PleaseService
public class LogoutCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        LOG.debug("Logout user {}", session.getAttribute(SessionConstant.CURRENT_USER));
        session.invalidate();
        return new CommandResult(Path.HOME, true);
    }
}
