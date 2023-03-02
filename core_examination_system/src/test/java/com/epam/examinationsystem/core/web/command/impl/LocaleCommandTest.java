package com.epam.examinationsystem.core.web.command.impl;

import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

@ExtendWith(MockitoExtension.class)
class LocaleCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(LocaleCommandTest.class);

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LocaleCommand localeCommand;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", LocaleCommand.class.getSimpleName());
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", LocaleCommand.class.getSimpleName());
    }

    @Test
    void shouldGetHomePage() {
        Mockito.when(request.getParameter(Path.LANGUAGE)).thenReturn("uk_UA");
        Mockito.when(request.getHeader(Mockito.anyString())).thenReturn("page");
        Mockito.when(request.getSession()).thenReturn(session);
        CommandResult actualResult = localeCommand.execute(request, response);
        Assertions.assertEquals("page", actualResult.getPage());
        Assertions.assertTrue(actualResult.isRedirect());
    }
}
