package com.epam.examinationsystem.core.web.command.impl.admin.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

/**
 * Extends the ActionCommand interface to provide a command to get the test edition page.
 */
@PleaseService
public class GetEditTestPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetEditTestPageCommand.class);

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
        LOG.debug("Forwarding to {}", Path.EDIT_TEST_PAGE);
        String uuid = request.getParameter(Parameter.UUID);
        if (ParameterValidator.isValidUUID(uuid)) {
            try {
                Optional<TestDto> test = testService.findByUuid(UUID.fromString(uuid));
                if (test.isPresent()) {
                    request.setAttribute(Attribute.TEST, test.get());
                    return new CommandResult(Path.EDIT_TEST_PAGE);
                }
            } catch (ServiceException e) {
                LOG.error("Error during getting tests edition page has been occurred {}", e.getMessage());
                return new CommandResult(Path.TESTS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
