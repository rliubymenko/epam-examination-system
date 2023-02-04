package com.epam.examinationsystem.core.web.command.impl.admin.question;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
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
public class GetAllQuestionsByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllQuestionsByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private QuestionService questionService;

    public GetAllQuestionsByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null, null),
                new HeaderName("question.type", true, "questionType", "question.type"),
                new HeaderName("question.content", true, "questionContent", "question.content"),
                new HeaderName("question.description", true, "questionDescription", "question.description"),
                new HeaderName("question.test", true, "testName", "test.name"),
                new HeaderName("table.actions", false, null, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Searching questions by parameters");
        String page = Path.QUESTIONS_PAGE;
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request, headerNames);
        try {
            DataTableResponse<QuestionDto> questionResponse = questionService.findAll(tableRequest);
            PageableFacade<QuestionDto> pageableFacade = new PageableFacade<>(tableRequest, questionResponse, headerNames);
            PageResponseDto<QuestionDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList();
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, true);
            request.setAttribute(Attribute.CREATE_NEW_ITEM_URL, "/admins/questions/question/new");
            request.setAttribute(Attribute.CARD_HEADER, "table.questions");
            request.setAttribute(Attribute.REPORT_PATH, "/admins/questions/report");
            return new CommandResult(page);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
