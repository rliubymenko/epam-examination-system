package com.epam.examinationsystem.core.web.command.impl.admin.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.TestDto;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Extends the ActionCommand interface to provide a command to edit the test.
 */
@PleaseService
public class EditTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditTestCommand.class);

    @PleaseInject
    private TestService testService;

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uuid = request.getParameter(Parameter.UUID);
            String name = request.getParameter(Parameter.NAME);
            String description = request.getParameter(Parameter.DESCRIPTION);
            String complexity = request.getParameter(Parameter.COMPLEXITY);
            String duration = request.getParameter(Parameter.DURATION);
            String expirationDate = request.getParameter(Parameter.EXPIRATION_DATE);
            String maxAttemptNumber = request.getParameter(Parameter.MAX_ATTEMPT_NUMBER);

            LOG.debug("Edit test with uuid: {}", uuid);
            if (ParameterValidator.isValidUUID(uuid) && testService.existsByUuid(UUID.fromString(uuid))) {
                TestDto currentTest = testService.findByUuid(UUID.fromString(uuid)).get();

                Set<String> inconsistencies = performValidation(name, duration, maxAttemptNumber);

                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid test data");
                    request.setAttribute(Attribute.TEST, currentTest);
                    request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                    return new CommandResult(Path.EDIT_TEST_PAGE);
                }

                TestDto test = TestDto.builder()
                        .setUuid(uuid)
                        .setName(name)
                        .setDescription(description)
                        .setComplexity(complexity)
                        .setDuration(duration)
                        .setExpirationDate(expirationDate)
                        .setMaxAttemptNumber(maxAttemptNumber)
                        .build();
                if (testService.update(test)) {
                    LOG.debug("The test {} has been updated successfully", test);
                    return new CommandResult(Path.TESTS, true);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during updating test has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_TEST_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    /**
     * Returns a set of strings containing the names of the parameters that failed the validation.
     *
     * @return the Set with strings.
     */
    private Set<String> performValidation(String name, String duration, String maxAttemptNumber) {
        Set<String> inconsistencies = new HashSet<>();
        if (StringUtils.isBlank(name)) {
            inconsistencies.add("name");
        }
        if (StringUtils.isBlank(duration) || ParameterValidator.isNotGreaterOrEqualTo(duration, 0)) {
            inconsistencies.add("duration");
        }
        if (StringUtils.isBlank(maxAttemptNumber) || ParameterValidator.isNotGreaterThan(maxAttemptNumber, 1)) {
            inconsistencies.add("maxAttemptNumber");
        }
        return inconsistencies;
    }
}
