package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
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
import java.util.List;
import java.util.Set;
import java.util.UUID;

@PleaseService
public class CreateTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CreateTestCommand.class);

    @PleaseInject
    private SubjectService subjectService;

    @PleaseInject
    private TestService testService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter(Parameter.NAME);
            String description = request.getParameter(Parameter.DESCRIPTION);
            String complexity = request.getParameter(Parameter.COMPLEXITY);
            String duration = request.getParameter(Parameter.DURATION);
            String expirationDate = request.getParameter(Parameter.EXPIRATION_DATE);
            String maxAttemptNumber = request.getParameter(Parameter.MAX_ATTEMPT_NUMBER);
            String subjectUuid = request.getParameter(Parameter.SUBJECT_UUID);

            Set<String> inconsistencies = performValidation(subjectUuid, name, duration, maxAttemptNumber);

            TestDto test = TestDto.builder()
                    .setName(name)
                    .setDescription(description)
                    .setComplexity(complexity)
                    .setDuration(duration)
                    .setExpirationDate(expirationDate)
                    .setMaxAttemptNumber(maxAttemptNumber)
                    .setSubject(new TestDto.SubjectForTest(subjectUuid, null))
                    .build();

            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid test data");
                List<SubjectDto> subjects = subjectService.findAll();
                request.setAttribute(Attribute.SUBJECTS, subjects);
                request.setAttribute(Attribute.TEST, test);
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.NEW_TEST_PAGE);
            }

            if (subjectService.existsByUuid(UUID.fromString(subjectUuid)) && testService.create(test)) {
                LOG.debug("The test {} has been created successfully", test);
                return new CommandResult(Path.TESTS, true);
            }
        } catch (ServiceException e) {
            LOG.error("Error during creating subject has been occurred {}", e.getMessage());
            return new CommandResult(Path.NEW_TEST_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    private Set<String> performValidation(String subjectUuid, String name, String duration, String maxAttemptNumber) {
        Set<String> inconsistencies = new HashSet<>();
        if (subjectUuid.equals("-1")) {
            inconsistencies.add("subject");
        }
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
