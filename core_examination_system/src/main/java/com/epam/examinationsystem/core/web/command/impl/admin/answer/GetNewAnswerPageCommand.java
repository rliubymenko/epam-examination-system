package com.epam.examinationsystem.core.web.command.impl.admin.answer;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
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
 * Extends the ActionCommand interface to provide a command to get the answer creation page.
 */
@PleaseService
public class GetNewAnswerPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetNewAnswerPageCommand.class);

    @PleaseInject
    private QuestionService questionService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.NEW_ANSWER_PAGE);
        try {
            List<QuestionDto> questions = questionService.findAllOpenToCreateAnswers();
            request.setAttribute(Attribute.QUESTIONS, questions);
            return new CommandResult(Path.NEW_ANSWER_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during redirecting to answer creating page has been occurred {}", e.getMessage());
            return new CommandResult(Path.HOME);
        }
    }
}
