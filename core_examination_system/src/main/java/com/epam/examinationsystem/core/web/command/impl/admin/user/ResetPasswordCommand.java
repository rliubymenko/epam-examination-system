package com.epam.examinationsystem.core.web.command.impl.admin.user;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.MailService;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.PasswordEncoder;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Extends the ActionCommand interface to provide a command to reset the password for current user.
 */
@PleaseService
public class ResetPasswordCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordCommand.class);

    @PleaseInject
    private UserService userService;

    @PleaseInject
    private MailService mailService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
        LOG.debug("Reset password for user {}", currentUser);
        try {
            String newPassword = PasswordEncoder.generateRandomPassword();
            if (userService.resetPassword(currentUser, newPassword)) {
                mailService.sendNewPassword(currentUser.getEmail(), newPassword);
            }
            return new CommandResult(Path.USERS, true);
        } catch (ServiceException e) {
            LOG.error("Error during resetting password has been occurred {}", e.getMessage());
            return new CommandResult(Path.USERS, true);
        }
    }
}
