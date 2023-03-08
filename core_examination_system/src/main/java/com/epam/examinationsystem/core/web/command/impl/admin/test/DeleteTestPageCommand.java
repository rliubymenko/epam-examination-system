package com.epam.examinationsystem.core.web.command.impl.admin.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Extends the ActionCommand interface to provide a command to delete the test.
 */
@PleaseService
public class DeleteTestPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteTestPageCommand.class);

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
        String uuid = request.getParameter(Parameter.UUID);
        LOG.debug("Deleting test by uuid {}", uuid);
        if (ParameterValidator.isValidUUID(uuid)) {
            try {
                testService.deleteByUuid(UUID.fromString(uuid));
                return new CommandResult(Path.TESTS, true);
            } catch (ServiceException e) {
                LOG.error("Error during deleting test has been occurred {}", e.getMessage());
                return new CommandResult(Path.TESTS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
