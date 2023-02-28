package com.epam.examinationsystem.core.util.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

class DateUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtilTest.class);

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", DateUtil.class.getSimpleName());
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", DateUtil.class.getSimpleName());
    }

    @Test
    void shouldParseDateTime() {
        LocalDateTime actualTime = DateUtil.parseDateTime("2015-08-04T10:11:30");
        Assertions.assertEquals(LocalDateTime.parse("2015-08-04T10:11:30"), actualTime);
    }

    @Test
    void shouldCompareDateForStudentAndReturnFalse() {
        String actualState = DateUtil.compareDateForStudent(LocalDateTime.parse("2015-08-04T10:11:30"));
        Assertions.assertEquals("false", actualState);
    }

    @Test
    void shouldCompareDateForStudentAndReturnTrue() {
        String actualState = DateUtil.compareDateForStudent(LocalDateTime.parse("2222-08-04T10:11:30"));
        String actualNullState = DateUtil.compareDateForStudent(null);
        Assertions.assertEquals("true", actualState);
        Assertions.assertEquals("true", actualNullState);
    }
}
