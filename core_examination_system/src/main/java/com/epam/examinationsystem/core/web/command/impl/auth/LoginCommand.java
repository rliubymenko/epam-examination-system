package com.epam.examinationsystem.core.web.command.impl.auth;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.di.annotation.PleaseValue;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.PasswordEncoder;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.CaptchaUtil;
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

import java.io.IOException;

/**
 * Extends the ActionCommand interface to provide a command to log in the user.
 */
@PleaseService
public class LoginCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    @PleaseValue("captcha.public_key")
    private String captchaPublicKey;

    @PleaseValue("captcha.secret_key")
    private String captchaSecretKey;

    @PleaseValue("captcha.verify_site_url")
    private String captchaVerifySiteUrl;

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
        LOG.debug("Trying to login user");
        try {
            String username = request.getParameter(Parameter.USERNAME);
            String password = request.getParameter(Parameter.PASSWORD);
            String gRecaptchaResponse = request.getParameter(Parameter.G_RECAPTCHA_RESPONSE);
            if (!CaptchaUtil.verifyCaptcha(gRecaptchaResponse, captchaVerifySiteUrl, captchaSecretKey)) {
                LOG.error("Captcha was not submitted {}", gRecaptchaResponse);
                request.setAttribute(Attribute.WRONG_CAPTCHA, true);
                return new CommandResult(Path.LOGIN_PAGE);
            }
            if (ParameterValidator.isNotValidUsername(username) || !userService.existsByUsername(username)) {
                LOG.error("User with current username: {} does not exist ", username);
                request.setAttribute(Attribute.WRONG_USERNAME, true);
                return new CommandResult(Path.LOGIN_PAGE);
            }
            UserDto userDto = userService.findByUsername(username).get();
            if (!userDto.getIsActivated()) {
                LOG.error("Login for user with current username: {} was denied ", username);
                request.setAttribute(Attribute.LOGIN_DENIED, true);
                return new CommandResult(Path.LOGIN_PAGE);
            }
            if (PasswordEncoder.isMatched(password, userDto.getPassword())) {
                LOG.debug("The user {} has been signed in successfully", userDto);
                HttpSession session = request.getSession();
                session.setAttribute(SessionConstant.CURRENT_USER, userDto);
                session.setAttribute(SessionConstant.IS_LOGGED_IN, true);
                return new CommandResult(Path.HOME, true);
            }
            LOG.error("Password matching error for user with username {}", username);
            request.setAttribute(Attribute.WRONG_PASSWORD, true);
            return new CommandResult(Path.LOGIN_PAGE);
        } catch (ServiceException | IOException e) {
            LOG.error("Error during login has been occurred {}", e.getMessage());
            return new CommandResult(Path.LOGIN_PAGE);
        }
    }
}
