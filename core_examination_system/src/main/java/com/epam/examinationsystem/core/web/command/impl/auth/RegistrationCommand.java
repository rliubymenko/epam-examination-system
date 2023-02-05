package com.epam.examinationsystem.core.web.command.impl.auth;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.di.annotation.PleaseValue;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.MailService;
import com.epam.examinationsystem.core.service.UserService;
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
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@PleaseService
public class RegistrationCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);

    @PleaseInject
    private UserService userService;

    @PleaseValue("captcha.public_key")
    private String captchaPublicKey;

    @PleaseValue("captcha.secret_key")
    private String captchaSecretKey;

    @PleaseValue("captcha.verify_site_url")
    private String captchaVerifySiteUrl;

    @PleaseInject
    private MailService mailService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Trying to register user");
        try {
            String username = request.getParameter(Parameter.USERNAME);
            String email = request.getParameter(Parameter.EMAIL);
            String password = request.getParameter(Parameter.PASSWORD);
            String repeatedPassword = request.getParameter(Parameter.REPEATED_PASSWORD);
            String firstName = request.getParameter(Parameter.FIRST_NAME);
            String lastName = request.getParameter(Parameter.LAST_NAME);
            String gRecaptchaResponse = request.getParameter(Parameter.G_RECAPTCHA_RESPONSE);
            if (!CaptchaUtil.verifyCaptcha(gRecaptchaResponse, captchaVerifySiteUrl, captchaSecretKey)) {
                LOG.error("Captcha was not submitted {}", gRecaptchaResponse);
                request.setAttribute(Attribute.WRONG_CAPTCHA, true);
                return new CommandResult(Path.REGISTRATION_PAGE);
            }
            Set<String> inconsistencies = performValidation(username, email, firstName, lastName, password, repeatedPassword);

            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid user credentials");
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.REGISTRATION_PAGE);
            }
            String language = (String) request.getSession().getAttribute(SessionConstant.LOCALE);
            UserDto userDto = UserDto.builder()
                    .setUsername(username)
                    .setPassword(password)
                    .setEmail(email)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .build();
            if (userService.createStudent(userDto)) {
                String fullName = userDto.getLastName() + " " + userDto.getFirstName();
                mailService.sendWelcomeMail(userDto.getEmail(), language, fullName);
                LOG.debug("The user {} has been created successfully", userDto);
                return new CommandResult(Path.LOGIN, true);
            }
        } catch (ServiceException | IOException e) {
            LOG.error("Error during registration has been occurred {}", e.getMessage());
            return new CommandResult(Path.REGISTRATION_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    private Set<String> performValidation(
            String username,
            String email,
            String firstName,
            String lastName,
            String password,
            String repeatedPassword) throws ServiceException {

        Set<String> inconsistencies = new HashSet<>();
        if (ParameterValidator.isNotValidUsername(username)) {
            inconsistencies.add("invalid_username");
        }
        if (userService.existsByUsername(username)) {
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
        return inconsistencies;
    }
}
