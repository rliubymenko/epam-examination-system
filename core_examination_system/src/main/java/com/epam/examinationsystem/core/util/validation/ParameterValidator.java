package com.epam.examinationsystem.core.util.validation;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

public final class ParameterValidator {

    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
    private static final String EMAIL_REGEX = "^([\\w\\-.]+)@([\\w\\-.]+)\\.([a-zA-Z]{2,10})$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,50}$";
    private static final String EN_US_REGEX = "^([A-Za-z])+$";
    private static final String UK_UA_REGEX = "^([А-Яа-яЁёЇїІіЄєҐґ'])+$";

    private ParameterValidator() {
    }

    public static boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }

    public static boolean isNotValidUsername(String username) {
        return username == null;
    }

    public static boolean isValidBoolean(String value) {
        return value == null || BooleanUtils.toBoolean(value);
    }

    public static boolean isNotValidEmail(String email) {
        return email == null || !Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isNotValidPassword(String password) {
        return password != null && Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isNotValidFirstName(String firstName) {
        return firstName == null ||
                (firstName.length() < 1 || firstName.length() > 30) ||
                !BooleanUtils.xor(new boolean[]{Pattern.matches(EN_US_REGEX, firstName), Pattern.matches(UK_UA_REGEX, firstName)});
    }

    public static boolean isNotValidLastName(String lastName) {
        return lastName == null ||
                (lastName.length() < 1 || lastName.length() > 50) ||
                !BooleanUtils.xor(new boolean[]{Pattern.matches(EN_US_REGEX, lastName), Pattern.matches(UK_UA_REGEX, lastName)});
    }

    public static boolean isNotGreaterOrEqualTo(String number, int limit) {
        int integer = Integer.parseInt(number);
        return integer <= limit;
    }

    public static boolean isNotGreaterThan(String number, int limit) {
        int integer = Integer.parseInt(number);
        return integer < limit;
    }

    public static boolean isNotEmptyArray(String[] array) {
        return Arrays.stream(array).allMatch(StringUtils::isNotBlank);
    }
}
