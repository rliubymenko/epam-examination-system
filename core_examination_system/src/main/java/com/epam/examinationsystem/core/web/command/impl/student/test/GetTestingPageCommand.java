package com.epam.examinationsystem.core.web.command.impl.student.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Extends the ActionCommand interface to provide a command to get the page for the testing.
 */
@PleaseService
public class GetTestingPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetTestingPageCommand.class);

    @PleaseInject
    private TestService testService;

    @PleaseInject
    private UserTestService userTestService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.STUDENT_TESTING_PAGE);
        String page = request.getHeader(Path.CURRENT_PAGE);
        String uuid = request.getParameter(Parameter.UUID);
        UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
        try {
            if (ParameterValidator.isValidUUID(uuid) && testService.existsByUuid(UUID.fromString(uuid))) {
                Optional<StudentTestDto> testDto = testService.findByUuidForTesting(UUID.fromString(uuid));
                if (testDto.isPresent()) {
                    LocalDateTime startTime = LocalDateTime.now();
                    String userUuid = currentUser.getUuid();
                    UserTestDto userTestDto = UserTestDto.builder()
                            .setTest(new UserTestDto.TestAdjacent(uuid, null))
                            .setUser(new UserTestDto.UserAdjacent(userUuid, currentUser.getUsername()))
                            .build();
                    Optional<UserTestDto> userTest = userTestService.findByUserAndTestUuid(UUID.fromString(userUuid), UUID.fromString(uuid));
                    UserTestDto savedUserTest;
                    if (userTest.isEmpty()) {
                        savedUserTest = userTestService.create(userTestDto);
                    } else {
                        savedUserTest = userTest.get();
                    }
                    Object currentStartTime = request.getSession().getAttribute(SessionConstant.START_TIME);
                    if (savedUserTest.getStartTime() == null || currentStartTime == null) {
                        userTestService.setStartTime(UUID.fromString(savedUserTest.getUuid()), startTime);
                        request.getSession().setAttribute(SessionConstant.START_TIME, startTime);
                    } else {
                        request.getSession().setAttribute(SessionConstant.START_TIME, savedUserTest.getStartTime());
                    }
                    request.setAttribute(Attribute.TEST, testDto.get());
                    return new CommandResult(Path.STUDENT_TESTING_PAGE);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during getting student testing page has been occurred {}", e.getMessage());
            return new CommandResult(page, true);
        }
        return new CommandResult(Path.HOME, true);
    }
}
