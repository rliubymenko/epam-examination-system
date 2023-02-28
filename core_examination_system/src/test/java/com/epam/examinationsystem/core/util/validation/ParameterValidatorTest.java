package com.epam.examinationsystem.core.util.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ParameterValidatorTest {

    private static final Logger LOG = LoggerFactory.getLogger(ParameterValidatorTest.class);

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", ParameterValidator.class.getSimpleName());
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", ParameterValidator.class.getSimpleName());
    }

    @Test
    void shouldPassUsernameValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidUsername("username"));
        Assertions.assertFalse(ParameterValidator.isNotValidUsername("sad"));
        Assertions.assertFalse(ParameterValidator.isNotValidUsername("s"));
        Assertions.assertFalse(ParameterValidator.isNotValidUsername("asdasda"));
    }

    @Test
    void shouldFailUsernameValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidUsername(""));
        Assertions.assertTrue(ParameterValidator.isNotValidUsername(null));
        Assertions.assertTrue(ParameterValidator.isNotValidUsername("   "));
        Assertions.assertTrue(ParameterValidator.isNotValidUsername("-=-=-0"));
    }

    @Test
    void shouldPassUuidValidation() {
        Assertions.assertTrue(ParameterValidator.isValidUUID("00000000-0000-0000-0000-000000000001"));
    }

    @Test
    void shouldFailUuidValidation() {
        Assertions.assertFalse(ParameterValidator.isValidUUID("000000-0000-000-0000-0000000001"));
        Assertions.assertFalse(ParameterValidator.isValidUUID(null));
        Assertions.assertFalse(ParameterValidator.isValidUUID("sdfsdf"));
    }

    @Test
    void shouldPassBooleanValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean(null));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("true"));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("false"));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("on"));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("off"));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("yes"));
        Assertions.assertFalse(ParameterValidator.isNotValidBoolean("no"));
    }

    @Test
    void shouldFailBooleanValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidBoolean(""));
        Assertions.assertTrue(ParameterValidator.isNotValidBoolean("  "));
        Assertions.assertTrue(ParameterValidator.isNotValidBoolean("sdf"));
    }

    @Test
    void shouldPassEmailValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidEmail("eaaseda@sad.asd"));
    }

    @Test
    void shouldFailEmailValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidEmail(""));
        Assertions.assertTrue(ParameterValidator.isNotValidEmail("  "));
        Assertions.assertTrue(ParameterValidator.isNotValidEmail(null));
        Assertions.assertTrue(ParameterValidator.isNotValidEmail("sdf"));
    }

    @Test
    void shouldPassPasswordValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidPassword("DDDsfsdf22"));
        Assertions.assertFalse(ParameterValidator.isNotValidPassword("DDD1111sssss"));
    }

    @Test
    void shouldFailPasswordValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidPassword(""));
        Assertions.assertTrue(ParameterValidator.isNotValidPassword("  "));
        Assertions.assertTrue(ParameterValidator.isNotValidPassword(null));
        Assertions.assertTrue(ParameterValidator.isNotValidPassword("sdf"));
        Assertions.assertTrue(ParameterValidator.isNotValidPassword("ssdfds12"));
        Assertions.assertTrue(ParameterValidator.isNotValidPassword("213123"));
    }

    @Test
    void shouldPassFirstnameValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidFirstName("roman"));
        Assertions.assertFalse(ParameterValidator.isNotValidFirstName("Olena"));
        Assertions.assertFalse(ParameterValidator.isNotValidFirstName("роман"));
        Assertions.assertFalse(ParameterValidator.isNotValidFirstName("Олена"));
    }

    @Test
    void shouldFailFirstnameValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName(""));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("  "));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName(null));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("f"));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("ііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііі"));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("ssdfівііі"));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("213123"));
        Assertions.assertTrue(ParameterValidator.isNotValidFirstName("фівфіВЫфвSSS"));
    }

    @Test
    void shouldPassLastnameValidation() {
        Assertions.assertFalse(ParameterValidator.isNotValidLastName("roman"));
        Assertions.assertFalse(ParameterValidator.isNotValidLastName("Olena"));
        Assertions.assertFalse(ParameterValidator.isNotValidLastName("роман"));
        Assertions.assertFalse(ParameterValidator.isNotValidLastName("Олена"));
    }

    @Test
    void shouldFailLastnameValidation() {
        Assertions.assertTrue(ParameterValidator.isNotValidLastName(""));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("  "));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName(null));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("f"));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("ііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііііі"));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("ssdfівііі"));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("213123"));
        Assertions.assertTrue(ParameterValidator.isNotValidLastName("фівфіВЫфвSSS"));
    }

    @Test
    void shouldPassIsNotGreaterOrEqualToValidation() {
        Assertions.assertTrue(ParameterValidator.isNotGreaterOrEqualTo("2", 6));
        Assertions.assertTrue(ParameterValidator.isNotGreaterOrEqualTo("6", 6));
    }

    @Test
    void shouldFailIsNotGreaterOrEqualToValidation() {
        Assertions.assertFalse(ParameterValidator.isNotGreaterOrEqualTo("7", 6));
        Assertions.assertFalse(ParameterValidator.isNotGreaterOrEqualTo("77", 6));
    }

    @Test
    void shouldPassIsNotGreaterThanValidation() {
        Assertions.assertTrue(ParameterValidator.isNotGreaterThan("2", 6));
        Assertions.assertTrue(ParameterValidator.isNotGreaterThan("5", 6));
    }

    @Test
    void shouldFailIsNotGreaterThanValidation() {
        Assertions.assertFalse(ParameterValidator.isNotGreaterThan("7", 6));
        Assertions.assertFalse(ParameterValidator.isNotGreaterThan("6", 6));
    }

    @Test
    void shouldPassIsNotEmptyArrayValidation() {
        Assertions.assertTrue(ParameterValidator.isNotEmptyArray(new String[]{"sd", "sdf"}));
    }

    @Test
    void shouldFailIsNotEmptyArrayValidation() {
        Assertions.assertFalse(ParameterValidator.isNotEmptyArray(new String[]{" ", "     ", ""}));
    }
}
