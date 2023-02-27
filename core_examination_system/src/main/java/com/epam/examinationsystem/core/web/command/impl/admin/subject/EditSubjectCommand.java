package com.epam.examinationsystem.core.web.command.impl.admin.subject;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
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

@PleaseService
public class EditSubjectCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(EditSubjectCommand.class);

    @PleaseInject
    private SubjectService subjectService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uuid = request.getParameter(Parameter.UUID);
            LOG.debug("Edit subject with uuid: {}", uuid);
            if (ParameterValidator.isValidUUID(uuid) && subjectService.existsByUuid(UUID.fromString(uuid))) {
                SubjectDto currentSubject = subjectService.findByUuid(UUID.fromString(uuid)).get();

                String name = request.getParameter(Parameter.NAME);
                String description = request.getParameter(Parameter.DESCRIPTION);

                Set<String> inconsistencies = performValidation(name, currentSubject);

                if (CollectionUtils.isNotEmpty(inconsistencies)) {
                    LOG.error("Invalid subject data");
                    request.setAttribute(Attribute.SUBJECT, currentSubject);
                    request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                    return new CommandResult(Path.EDIT_SUBJECT_PAGE);
                }
                SubjectDto subject = SubjectDto.builder()
                        .setUuid(uuid)
                        .setName(name)
                        .setDescription(description)
                        .build();
                if (subjectService.update(subject)) {
                    LOG.debug("The subject {} has been updated successfully", subject);
                    return new CommandResult(Path.SUBJECTS, true);
                }
            }
        } catch (ServiceException e) {
            LOG.error("Error during updating subject has been occurred {}", e.getMessage());
            return new CommandResult(Path.EDIT_SUBJECT_PAGE);
        }
        return new CommandResult(Path.HOME, true);
    }

    private Set<String> performValidation(String name, SubjectDto currentSubject) throws ServiceException {
        Set<String> inconsistencies = new HashSet<>();
        if (StringUtils.isBlank(name)) {
            inconsistencies.add("name");
        }
        if (subjectService.existsByName(name) && !currentSubject.getName().equals(name)) {
            inconsistencies.add("used_name");
        }
        return inconsistencies;
    }
}
