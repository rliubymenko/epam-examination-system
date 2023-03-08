package com.epam.examinationsystem.core.web.command.impl.admin.question;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Extends the ActionCommand interface to provide a command to get the question creation page.
 */
@PleaseService
public class GetNewQuestionPageForTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetNewQuestionPageForTestCommand.class);

    @PleaseInject
    private TestService testService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.NEW_QUESTION_PAGE);
        String testUuid = request.getParameter(Parameter.TEST_UUID);
        try {
            if (StringUtils.isBlank(testUuid)) {
                List<TestDto> tests = testService.findAll();
                request.setAttribute(Attribute.TESTS, tests);
            }
            request.setAttribute(Attribute.TEST_UUID, testUuid);
            return new CommandResult(Path.NEW_QUESTION_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during redirecting to question creating page has been occurred {}", e.getMessage());
            return new CommandResult(Path.HOME);
        }
    }
}
