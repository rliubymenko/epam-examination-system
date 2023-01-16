package com.epam.examinationsystem.core.web.command.impl.admin.subject;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@PleaseService
public class DeleteSubjectPageCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(DeleteSubjectPageCommand.class);

    @PleaseInject
    private SubjectService subjectService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        LOG.debug("Deleting subject by uuid {}", uuid);
        if (ParameterValidator.isValidUUID(uuid)) {
            try {
                subjectService.deleteByUuid(UUID.fromString(uuid));
                return new CommandResult(Path.SUBJECTS, true);
            } catch (ServiceException e) {
                LOG.error("Error during deleting subject has been occurred {}", e.getMessage());
                return new CommandResult(Path.SUBJECTS, true);
            }
        }
        return new CommandResult(Path.HOME, true);
    }
}
