package com.epam.examinationsystem.core.web.command.impl.admin.user;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
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
 * Extends the ActionCommand interface to provide a command to get the user edition page.
 */
@PleaseService
public class GetEditUserPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetEditUserPageCommand.class);

    @PleaseInject
    private UserService userService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.EDIT_USER_PAGE);
        String uuid = request.getParameter(Parameter.UUID);
        if (ParameterValidator.isValidUUID(uuid)) {
            try {
                Optional<UserDto> user = userService.findByUuid(UUID.fromString(uuid));
                if (user.isPresent()) {
                    request.setAttribute(Attribute.USER, user.get());
                    return new CommandResult(Path.EDIT_USER_PAGE);
                }
            } catch (ServiceException e) {
                LOG.error("Error during getting user edition page has been occurred {}", e.getMessage());
                return new CommandResult(Path.USERS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
