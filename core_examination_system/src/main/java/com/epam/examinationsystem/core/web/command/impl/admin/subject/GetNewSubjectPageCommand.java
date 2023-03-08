package com.epam.examinationsystem.core.web.command.impl.admin.subject;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extends the ActionCommand interface to provide a command to get the subject creation page.
 */
@PleaseService
public class GetNewSubjectPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetNewSubjectPageCommand.class);

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.NEW_SUBJECT_PAGE);
        return new CommandResult(Path.NEW_SUBJECT_PAGE);
    }
}
