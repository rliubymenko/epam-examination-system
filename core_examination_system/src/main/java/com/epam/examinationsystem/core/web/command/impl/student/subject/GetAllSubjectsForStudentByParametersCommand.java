package com.epam.examinationsystem.core.web.command.impl.student.subject;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentSubjectDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.facade.PageableFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@PleaseService
public class GetAllSubjectsForStudentByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllSubjectsForStudentByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private SubjectService subjectService;

    public GetAllSubjectsForStudentByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null),
                new HeaderName("subject.name", true, "name"),
                new HeaderName("subject.description", true, "description"),
                new HeaderName("table.actions", false, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Searching subjects by parameters for students");
        String page = Path.SUBJECTS_FOR_STUDENT_PAGE;
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request);
        try {
            DataTableResponse<StudentSubjectDto> subjectResponse = subjectService.findAllForStudent(tableRequest);
            PageableFacade<StudentSubjectDto> pageableFacade = new PageableFacade<>(tableRequest, subjectResponse);
            PageResponseDto<StudentSubjectDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList(headerNames);
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, false);
            request.setAttribute(Attribute.CARD_HEADER, "table.subjects");
            return new CommandResult(page);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
