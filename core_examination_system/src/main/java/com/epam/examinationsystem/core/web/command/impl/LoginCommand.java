package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.PasswordEncoder;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PleaseService
public class LoginCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    @PleaseInject
    private UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter(Parameter.USERNAME);
            String password = request.getParameter(Parameter.PASSWORD);
            if (!ParameterValidator.isValidUsername(username) || !userService.existsByUsername(username)) {
                LOG.error("User with current username: {} does not exist ", username);
                request.setAttribute(Attribute.WRONG_USERNAME, true);
                return new CommandResult(Path.LOGIN_PAGE);
            }
            UserDto userDto = userService.findByUsername(username).get();
            if (PasswordEncoder.isMatched(password, userDto.getPassword())) {
                LOG.debug("The user {} has been signed in successfully", userDto);
                HttpSession session = request.getSession();
                session.setAttribute(SessionConstant.USER, userDto);
                session.setAttribute(SessionConstant.IS_LOGGED_IN, true);
                return new CommandResult(Path.HOME, true);
            }
            LOG.error("Password matching error for user with username {}", username);
            request.setAttribute(Attribute.WRONG_PASSWORD, true);
            return new CommandResult(Path.LOGIN_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during login has been occurred {}", e.getMessage());
            return new CommandResult(Path.LOGIN_PAGE);
        }
    }
}
