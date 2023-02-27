package com.epam.examinationsystem.core.web.command.impl.student.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@PleaseService
public class SelectTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(SelectTestCommand.class);

    @PleaseInject
    private TestService testService;

    @PleaseInject
    private UserTestService userTestService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        String page = request.getHeader(Path.CURRENT_PAGE);
        try {
            if (ParameterValidator.isValidUUID(uuid) && testService.existsByUuid(UUID.fromString(uuid))) {
                UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
                UserTestDto userTestDto = UserTestDto.builder()
                        .setTest(new UserTestDto.TestAdjacent(uuid, null))
                        .setUser(new UserTestDto.UserAdjacent(currentUser.getUuid(), currentUser.getUsername()))
                        .build();
                LOG.debug("Selecting test for user: {}", userTestDto);
                userTestService.create(userTestDto);
                LOG.debug("The user test {} has been created successfully", userTestDto);
                return new CommandResult(page, true);
            }
        } catch (Exception e) {
            LOG.error("Error during selecting test has been occurred {}", e.getMessage());
            return new CommandResult(page, true);
        }
        return new CommandResult(Path.HOME, true);
    }
}
