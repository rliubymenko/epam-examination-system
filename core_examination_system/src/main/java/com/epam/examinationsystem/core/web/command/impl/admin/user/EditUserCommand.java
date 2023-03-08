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
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Extends the ActionCommand interface to provide a command to edit the user.
 */
@PleaseService
public class EditUserCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditUserCommand.class);

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
        try {
            String uuid = request.getParameter(Parameter.UUID);
            LOG.debug("Edit user with uuid: {}", uuid);
            UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
            if (ParameterValidator.isValidUUID(uuid) && userService.existsByUuid(UUID.fromString(uuid))) {
                UserDto user = userService.findByUuid(UUID.fromString(uuid)).get();

                String username = request.getParameter(Parameter.USERNAME);
                String email = request.getParameter(Parameter.EMAIL);
                String firstName = request.getParameter(Parameter.FIRST_NAME);
                String lastName = request.getParameter(Parameter.LAST_NAME);
                String isActivated = request.getParameter(Parameter.IS_ACTIVATED);

                Set<String> inconsistencies = performValidation(username, email, firstName, lastName, user);

                boolean isLoggedUser = currentUser.getUuid().equals(user.getUuid());
                if (!isLoggedUser && ParameterValidator.isNotValidBoolean(isActivated)) {
                    inconsistencies.add("isActivated");
                }
                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid user credentials");
                    request.setAttribute(Attribute.USER, user);
                    request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                    return new CommandResult(Path.EDIT_USER_PAGE);
                }
                UserDto userDto = UserDto.builder()
                        .setUuid(uuid)
                        .setUsername(username)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setIsActivated(BooleanUtils.toBoolean(isActivated))
                        .build();
                if (userService.update(userDto, isLoggedUser)) {
                    if (isLoggedUser) {
                        UserDto updatedUser = userService.findByUuid(UUID.fromString(uuid)).get();
                        request.getSession().setAttribute(SessionConstant.CURRENT_USER, updatedUser);
                    }
                    LOG.debug("The user {} has been updated successfully", userDto);
                    return new CommandResult(Path.USERS, true);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during updating user has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_USER_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    /**
     * Returns a set of strings containing the names of the parameters that failed the validation.
     *
     * @return the Set with strings.
     */
    private Set<String> performValidation(
            String username,
            String email,
            String firstName,
            String lastName,
            UserDto currentUser) throws ServiceException {

        Set<String> inconsistencies = new HashSet<>();

        if (ParameterValidator.isNotValidUsername(username)) {
            inconsistencies.add("username");
        }
        if (userService.existsByUsername(username) && !currentUser.getUsername().equals(username)) {
            inconsistencies.add("used_username");
        }
        if (ParameterValidator.isNotValidEmail(email)) {
            inconsistencies.add("email");
        }
        if (userService.existsByEmail(email) && !currentUser.getEmail().equals(email)) {
            inconsistencies.add("used_email");
        }
        if (ParameterValidator.isNotValidFirstName(firstName)) {
            inconsistencies.add("firstName");
        }
        if (ParameterValidator.isNotValidLastName(lastName)) {
            inconsistencies.add("lastName");
        }
        return inconsistencies;
    }
}
