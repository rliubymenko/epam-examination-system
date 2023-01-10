package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@PleaseService
public class EditUserCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditUserCommand.class);

    @PleaseInject
    private UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uuid = request.getParameter(Parameter.UUID);
            if (ParameterValidator.isValidUUID(uuid)) {
                Set<String> inconsistencies = new HashSet<>();
                String username = request.getParameter(Parameter.USERNAME);
                String email = request.getParameter(Parameter.EMAIL);
                String password = request.getParameter(Parameter.PASSWORD);
                String firstName = request.getParameter(Parameter.FIRST_NAME);
                String lastName = request.getParameter(Parameter.LAST_NAME);
                String isActivated = request.getParameter(Parameter.IS_ACTIVATED);

                if (!ParameterValidator.isValidUsername(username)) {
                    inconsistencies.add("username");
                }
                if (!ParameterValidator.isValidEmail(email)) {
                    inconsistencies.add("email");
                }
                if (!ParameterValidator.isValidFirstName(firstName)) {
                    inconsistencies.add("firstName");
                }
                if (!ParameterValidator.isValidLastName(lastName)) {
                    inconsistencies.add("lastName");
                }
                if (!ParameterValidator.isValidBoolean(isActivated)) {
                    inconsistencies.add("isActivated");
                }
                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid user credentials");
                    Optional<UserDto> user = userService.findByUuid(UUID.fromString(uuid));
                    request.setAttribute("user", user.get());
                    request.setAttribute("inconsistencies", inconsistencies);
                    return new CommandResult(Path.EDIT_USER_PAGE);
                }
                UserDto userDto = UserDto.builder()
                        .setUuid(uuid)
                        .setUsername(username)
                        .setPassword(password)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setIsActivated(BooleanUtils.toBoolean(isActivated))
                        .build();
                if (userService.update(userDto)) {
                    LOG.debug("The user {} has been updated successfully", userDto);
                    return new CommandResult(Path.USERS, true);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during registration has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_USER_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }
}
