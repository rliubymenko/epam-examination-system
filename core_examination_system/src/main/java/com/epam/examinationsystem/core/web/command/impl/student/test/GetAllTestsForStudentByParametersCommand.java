package com.epam.examinationsystem.core.web.command.impl.student.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import com.epam.examinationsystem.core.web.facade.PageableFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@PleaseService
public class GetAllTestsForStudentByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllTestsForStudentByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private TestService testService;

    public GetAllTestsForStudentByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null),
                new HeaderName("test.name", true, "name"),
                new HeaderName("test.description", true, "description"),
                new HeaderName("test.complexity", true, "complexity"),
                new HeaderName("test.duration", true, "duration"),
                new HeaderName("test.creationDate", true, "created"),
                new HeaderName("test.expirationDate", true, "expiration_date"),
                new HeaderName("test.maxAttemptNumber", true, "max_attempt_number"),
                new HeaderName("test.totalAttemptNumber", true, "total_attempt_number"),
                new HeaderName("test.subject", false, "subject_id"),
                new HeaderName("table.actions", false, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Searching tests by parameters");
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request);
        try {
            UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
            DataTableResponse<TestDto> subjectResponse = testService.findAllForStudent(
                    tableRequest, UUID.fromString(currentUser.getUuid())
            );
            PageableFacade<TestDto> pageableFacade = new PageableFacade<>(tableRequest, subjectResponse);
            PageResponseDto<TestDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList(headerNames);
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, false);
            request.setAttribute(Attribute.CARD_HEADER, "table.tests");
            return new CommandResult(Path.TESTS_FOR_STUDENT_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
