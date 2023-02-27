package com.epam.examinationsystem.core.web.command.impl.student.account;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@PleaseService
public class GetStudentAccountPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetStudentAccountPageCommand.class);

    @PleaseInject
    private UserTestService userTestService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.STUDENT_ACCOUNT_PAGE);
        UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
        try {
            List<UserTestDto> userTests = userTestService.findByUserUuid(UUID.fromString(currentUser.getUuid()));
            request.setAttribute(Attribute.USER_TESTS, userTests);
            return new CommandResult(Path.STUDENT_ACCOUNT_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during getting student account page has been occurred {}", e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
