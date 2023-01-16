package com.epam.examinationsystem.core.web.command.impl.admin.user;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
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
public class GetAllUsersByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllUsersByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private UserService userService;

    public GetAllUsersByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null),
                new HeaderName("user.username", true, "username"),
                new HeaderName("user.email", true, "email"),
                new HeaderName("user.first_name", true, "first_name"),
                new HeaderName("user.last_name", true, "last_name"),
                new HeaderName("user.is_activated", true, "is_activated"),
                new HeaderName("user.role", false, "role_id"),
                new HeaderName("table.editing", false, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Searching users by parameters");
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request);
        try {
            DataTableResponse<UserDto> userResponse = userService.findAll(tableRequest);
            PageableFacade<UserDto> pageableFacade = new PageableFacade<>(tableRequest, userResponse);
            PageResponseDto<UserDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList(headerNames);
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, false);
            request.setAttribute(Attribute.CARD_HEADER, "table.users");
            return new CommandResult(Path.USERS_PAGE);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
