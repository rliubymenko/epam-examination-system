package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extends the ActionCommand interface to provide a command to change the current locale of the system.
 */
@PleaseService
public class LocaleCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(LocaleCommand.class);

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(Path.LANGUAGE);
        request.getSession().setAttribute(SessionConstant.LOCALE, language);
        String page = request.getHeader(Path.CURRENT_PAGE);
        LOG.debug("Changing the locale language to {}", language);
        return new CommandResult(page, true);
    }
}
