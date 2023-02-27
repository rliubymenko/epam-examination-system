package com.epam.examinationsystem.core.web.command.impl.admin.usertest;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserTestService;
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
public class GetAllUserTestsByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllUserTestsByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private UserTestService userTestService;

    public GetAllUserTestsByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null, null),
                new HeaderName("user_test.user", true, "username", "epam_user.username"),
                new HeaderName("user_test.test", true, "testName", "test.name"),
                new HeaderName("user_test.start_time", true, "testStartTime", "epam_user_test.start_time"),
                new HeaderName("user_test.end_time", true, "testEndTime", "epam_user_test.end_time"),
                new HeaderName("user_test.is_selected", true, "selected", "epam_user_test.is_selected"),
                new HeaderName("user_test.is_completed", true, "completed", "epam_user_test.is_completed"),
                new HeaderName("user_test.mark_value", true, "userMark", "epam_user_test.mark_value"),
                new HeaderName("user_test.attempt_number", true, "attemptNumber", "epam_user_test.attempt_number"),
                new HeaderName("table.actions", false, null, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page = Path.USER_TESTS_PAGE;
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request, headerNames);
        LOG.debug("Searching usertests by parameters: {}", tableRequest);
        try {
            DataTableResponse<UserTestDto> userTestResponse = userTestService.findAll(tableRequest);
            PageableFacade<UserTestDto> pageableFacade = new PageableFacade<>(tableRequest, userTestResponse, headerNames);
            PageResponseDto<UserTestDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList();
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, false);
            request.setAttribute(Attribute.CARD_HEADER, "table.user_tests");
            request.setAttribute(Attribute.REPORT_PATH, "/admins/usertests/report");
            return new CommandResult(page);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
