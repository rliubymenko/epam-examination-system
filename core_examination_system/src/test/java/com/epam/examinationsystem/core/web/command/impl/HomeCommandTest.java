package com.epam.examinationsystem.core.web.command.impl;

import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.web.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class HomeCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(HomeCommandTest.class);

    @Mock
    private SubjectService subjectService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private HomeCommand homeCommand;

    private SubjectDto subjectDto;
    private List<SubjectDto> subjectDtos;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", HomeCommand.class.getSimpleName());
        subjectDto = SubjectDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .build();
        subjectDtos = List.of(subjectDto);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", HomeCommand.class.getSimpleName());
    }

    @Test
    void shouldGetHomePage() throws ServiceException {
        Mockito.when(subjectService.findAll()).thenReturn(subjectDtos);
        CommandResult actualResult = homeCommand.execute(request, response);
        Assertions.assertEquals("/view/shared/home.jsp", actualResult.getPage());
        Assertions.assertFalse(actualResult.isRedirect());
    }
}
