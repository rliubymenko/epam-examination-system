package com.epam.examinationsystem.core.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.PageContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class DateTimeFormatterTagTest {

    private static final Logger LOG = LoggerFactory.getLogger(DateTimeFormatterTagTest.class);

    @Mock
    private PageContext pageContext;

    @InjectMocks
    private DateTimeFormatterTag tag;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", DateTimeFormatterTag.class.getSimpleName());
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", DateTimeFormatterTag.class.getSimpleName());
    }

    @Test
    void shouldReturnDataTimeInSpecificFormat() throws JspException, IOException {
        tag.setDatetime("2015-08-04T10:11:30");
        JspWriter jspWriter = Mockito.mock(JspWriter.class);
        Mockito.when(pageContext.getOut()).thenReturn(jspWriter);
        Mockito.doNothing().when(jspWriter).print(Mockito.any(String.class));
        int tagCode = tag.doStartTag();
        ArgumentCaptor<String> timeCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(jspWriter, Mockito.times(1)).print(timeCaptor.capture());
        Assertions.assertEquals("4 авг. 2015 г., 10:11:30", timeCaptor.getValue());
        Assertions.assertEquals(0, tagCode);
    }
}
