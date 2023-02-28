package com.epam.examinationsystem.core.util.web;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class PageableUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(PageableUtilTest.class);

    private HttpServletRequest request;
    private DataTableRequest tableRequest;
    private List<HeaderName> headerNames;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", PageableUtil.class.getSimpleName());
        request = Mockito.mock(HttpServletRequest.class);
        tableRequest = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        headerNames = List.of(
                new HeaderName("#", false, null, null),
                new HeaderName("answer.content", true, "answerContent", "answer.content"),
                new HeaderName("answer.is_correct", true, "answerCorrectness", "answer.is_correct"),
                new HeaderName("answer.question", true, "questionContent", "question.content"),
                new HeaderName("answer.question_type", true, "questionType", "question.type"),
                new HeaderName("table.actions", false, null, null)
        );
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", PageableUtil.class.getSimpleName());
    }

    @Test
    void shouldExtractPageableData() {
        DataTableRequest dataTableRequest = PageableUtil.extractPageableData(request, headerNames);
        Assertions.assertEquals(1, dataTableRequest.getCurrentPage());
        Assertions.assertEquals(10, dataTableRequest.getPageSize());
        Assertions.assertEquals("desc", dataTableRequest.getOrder());
        Assertions.assertEquals("created", dataTableRequest.getSort());
        Assertions.assertEquals("", dataTableRequest.getSearchQuery());
        Assertions.assertEquals("-1", dataTableRequest.getSearchUuid());
    }

    @Test
    void shouldCalculatePageableData() {
        DataTableResponse<AnswerDto> answerDtoDataTableResponse = PageableUtil.<AnswerDto>calculatePageableData(tableRequest, 1);
        Assertions.assertEquals(0, answerDtoDataTableResponse.getDtos().size());
        Assertions.assertEquals(0, answerDtoDataTableResponse.getDataForSearch().size());
        Assertions.assertEquals(0, answerDtoDataTableResponse.getCurrentDataForSearch().size());
        Assertions.assertEquals(1, answerDtoDataTableResponse.getEntitiesSize());
        Assertions.assertEquals(1, answerDtoDataTableResponse.getEntriesFrom());
        Assertions.assertEquals(1, answerDtoDataTableResponse.getEntriesTo());
        Assertions.assertEquals(1, answerDtoDataTableResponse.getTotalPageSize());
        Assertions.assertEquals("", answerDtoDataTableResponse.getSearchQuery());
    }
}
