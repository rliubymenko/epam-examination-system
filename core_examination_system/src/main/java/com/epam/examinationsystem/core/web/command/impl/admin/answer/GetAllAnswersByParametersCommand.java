package com.epam.examinationsystem.core.web.command.impl.admin.answer;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
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
public class GetAllAnswersByParametersCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllAnswersByParametersCommand.class);
    private final List<HeaderName> headerNames;

    @PleaseInject
    private AnswerService answerService;

    public GetAllAnswersByParametersCommand() {
        headerNames = List.of(
                new HeaderName("#", false, null, null),
                new HeaderName("answer.content", true, "answerContent", "answer.content"),
                new HeaderName("answer.is_correct", true, "answerCorrectness", "answer.is_correct"),
                new HeaderName("answer.question", true, "questionContent", "question.content"),
                new HeaderName("answer.question_type", true, "questionType", "question.type"),
                new HeaderName("table.actions", false, null, null)
        );
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page = Path.ANSWERS_PAGE;
        DataTableRequest tableRequest = PageableUtil.extractPageableData(request, headerNames);
        LOG.debug("Searching answers by parameters: {}", tableRequest);
        try {
            DataTableResponse<AnswerDto> answerResponse = answerService.findAll(tableRequest);
            PageableFacade<AnswerDto> pageableFacade = new PageableFacade<>(tableRequest, answerResponse, headerNames);
            PageResponseDto<AnswerDto> pageResponseDto = pageableFacade.getPageResponseDto();
            List<HeaderData> headerDataList = pageableFacade.getHeaderDataList();
            request.setAttribute(Attribute.HEADER_DATA_LIST, headerDataList);
            request.setAttribute(Attribute.PAGE_DATA, pageResponseDto);
            request.setAttribute(Attribute.ALLOW_CREATE, true);
            request.setAttribute(Attribute.CREATE_NEW_ITEM_URL, "/admins/answers/answer/new");
            request.setAttribute(Attribute.CARD_HEADER, "table.answers");
            request.setAttribute(Attribute.REPORT_PATH, "/admins/answers/report");
            return new CommandResult(page);
        } catch (ServiceException e) {
            LOG.error("Error during searching by {} has been occurred {}", tableRequest, e.getMessage());
            return new CommandResult(Path.HOME, true);
        }
    }
}
