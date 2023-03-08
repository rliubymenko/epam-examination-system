package com.epam.examinationsystem.core.web.command.impl.admin.subject;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.SubjectDto;
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

/**
 * Extends the ActionCommand interface to provide a command to get all subjects by parameters.
 */
@PleaseService
public class GetAllSubjectsByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllSubjectsByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private SubjectService subjectService;

    public GetAllSubjectsByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null, null),
                new HeaderName("subject.name", true, "subjectName", "subject.name"),
                new HeaderName("subject.description", true, "subjectDescription", "subject.description"),
                new HeaderName("table.actions", false, null, null)
        );
    }

    /**
     * Returns the CommandResult instance that contains the following page and the redirect flag.
     *
     * @param request  the HttpServletRequest instance.
     * @param response the HttpServletResponse instance.
     * @return the CommandResult instance.
     */
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page = Path.SUBJECTS_PAGE;
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request, headerNames);
        LOG.debug("Searching subjects by parameters: {}", tableRequest);
        try {
            DataTableResponse<SubjectDto> subjectResponse = subjectService.findAll(tableRequest);
            PageableFacade<SubjectDto> pageableFacade = new PageableFacade<>(tableRequest, subjectResponse, headerNames);
            PageResponseDto<SubjectDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList();
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, true);
            request.setAttribute(Attribute.CREATE_NEW_ITEM_URL, "/admins/subjects/subject/new");
            request.setAttribute(Attribute.CARD_HEADER, "table.subjects");
            request.setAttribute(Attribute.REPORT_PATH, "/admins/subjects/report");
            return new CommandResult(page);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
