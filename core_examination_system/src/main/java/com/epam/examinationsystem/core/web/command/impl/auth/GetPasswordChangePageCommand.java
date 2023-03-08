package com.epam.examinationsystem.core.web.command.impl.auth;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extends the ActionCommand interface to provide a command to get the password change page.
 */
@PleaseService
public class GetPasswordChangePageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetPasswordChangePageCommand.class);

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.PASSWORD_CHANGE_PAGE);
        return new CommandResult(Path.PASSWORD_CHANGE_PAGE);
    }
}
