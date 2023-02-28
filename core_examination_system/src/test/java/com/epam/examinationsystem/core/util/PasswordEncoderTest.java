package com.epam.examinationsystem.core.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class PasswordEncoderTest {

    private static final Logger LOG = LoggerFactory.getLogger(PasswordEncoderTest.class);

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", PasswordEncoder.class.getSimpleName());
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", PasswordEncoder.class.getSimpleName());
    }

    @Test
    void shouldMatchPassword() {
        Assertions.assertTrue(PasswordEncoder.isMatched("romashka123", "$2a$12$0QQ9vSZOlW.EpA/QOLlEsu17D8uUmQsnOQ1PhSo1d2/bsgrQV3Cui"));
    }
}
