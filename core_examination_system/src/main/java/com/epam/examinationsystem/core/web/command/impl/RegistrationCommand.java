package com.epam.examinationsystem.core.web.command.impl;

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
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@PleaseService
public class RegistrationCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);

    @PleaseInject
    private UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<String> inconsistencies = new HashSet<>();

            String username = request.getParameter(Parameter.USERNAME);
            String email = request.getParameter(Parameter.EMAIL);
            String password = request.getParameter(Parameter.PASSWORD);
            String repeatedPassword = request.getParameter(Parameter.REPEATED_PASSWORD);
            String firstName = request.getParameter(Parameter.FIRST_NAME);
            String lastName = request.getParameter(Parameter.LAST_NAME);

            if (ParameterValidator.isNotValidUsername(username) || userService.existsByUsername(username)) {
                inconsistencies.add("username");
            }
            if (ParameterValidator.isNotValidEmail(email) || userService.existsByEmail(email)) {
                inconsistencies.add("email");
            }
            if (ParameterValidator.isNotValidPassword(password)) {
                inconsistencies.add("password");
            }
            if (!password.equals(repeatedPassword)) {
                inconsistencies.add("repeatedPassword");
            }
            if (ParameterValidator.isNotValidFirstName(firstName)) {
                inconsistencies.add("firstName");
            }
            if (ParameterValidator.isNotValidLastName(lastName)) {
                inconsistencies.add("lastName");
            }
            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid user credentials");
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.REGISTRATION_PAGE);
            }
            UserDto userDto = UserDto.builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .build();
            if (userService.createStudent(userDto)) {
                LOG.debug("The user {} has been created successfully", userDto);
                return new CommandResult(Path.LOGIN, true);
            }
        } catch (ServiceException e) {
            LOG.error("Error during registration has been occurred {}", e.getMessage());
            return new CommandResult(Path.REGISTRATION_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }
}
