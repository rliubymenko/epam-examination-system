package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Extends the ActionCommand interface to provide a command to get home page.
 */
@PleaseService
public class HomeCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(HomeCommand.class);

    @PleaseInject
    private SubjectService subjectService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<SubjectDto> subjects = subjectService.findAll();
            request.setAttribute(Attribute.SUBJECTS, subjects);
            return new CommandResult(Path.HOME_PAGE);
        } catch (ServiceException e) {
            LOG.debug("Go to home page: {}", Path.HOME);
            return new CommandResult(Path.HOME_PAGE);
        }
    }
}
