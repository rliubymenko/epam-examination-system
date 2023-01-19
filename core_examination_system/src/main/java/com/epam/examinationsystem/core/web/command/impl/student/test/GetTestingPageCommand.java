package com.epam.examinationsystem.core.web.command.impl.student.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@PleaseService
public class GetTestingPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetTestingPageCommand.class);

    @PleaseInject
    private TestService testService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Forwarding to {}", Path.STUDENT_TESTING_PAGE);
        String page = request.getHeader(Path.CURRENT_PAGE);
        String uuid = request.getParameter(Parameter.UUID);
        try {
            if (ParameterValidator.isValidUUID(uuid) && testService.existsByUuid(UUID.fromString(uuid))) {
                Optional<StudentTestDto> testDto = testService.findByUuidForTesting(UUID.fromString(uuid));
                if (testDto.isPresent()) {
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
