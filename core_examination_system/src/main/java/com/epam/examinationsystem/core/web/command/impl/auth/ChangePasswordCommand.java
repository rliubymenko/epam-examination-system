package com.epam.examinationsystem.core.web.command.impl.auth;

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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Extends the ActionCommand interface to provide a command to change the password.
 */
@PleaseService
public class ChangePasswordCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ChangePasswordCommand.class);

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
        LOG.debug("Trying to change password for current user");
        try {
            String oldPassword = request.getParameter(Parameter.OLD_PASSWORD);
            String newPassword = request.getParameter(Parameter.NEW_PASSWORD);
            String repeatedPassword = request.getParameter(Parameter.REPEATED_PASSWORD);
            UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
            Set<String> inconsistencies = performValidation(currentUser.getPassword(), oldPassword, newPassword, repeatedPassword);

            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid user passwords");
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.PASSWORD_CHANGE_PAGE);
            }

            if (userService.resetPassword(currentUser, newPassword)) {
                LOG.debug("The password has been changed in {}", currentUser);
                return new CommandResult(Path.HOME, true);
            }
        } catch (ServiceException e) {
            LOG.error("Error during changing password has been occurred {}", e.getMessage());
            return new CommandResult(Path.PASSWORD_CHANGE_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    /**
     * Returns a set of strings containing the names of the parameters that failed the validation.
     *
     * @return the Set with strings.
     */
    private Set<String> performValidation(
            String realPassword,
            String oldPassword,
            String newPassword,
            String repeatedPassword) {
        Set<String> inconsistencies = new HashSet<>();
        if (!PasswordEncoder.isMatched(oldPassword, realPassword)) {
            inconsistencies.add("oldPassword");
        }
        if (ParameterValidator.isNotValidPassword(newPassword)) {
            inconsistencies.add("newPassword");
        }
        if (!StringUtils.equals(newPassword, repeatedPassword)) {
            inconsistencies.add("repeatedPassword");
        }
        return inconsistencies;
    }
}

