package com.epam.examinationsystem.core.util.validation;

import java.util.regex.Pattern;

public final class ParameterValidator {

    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
    private static final String EMAIL_REGEX = "^([\\w\\-.]+)@([\\w\\-.]+)\\.([a-zA-Z]{2,10})$";
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,50}$";
    private static final String FIRST_NAME_REGEX = "[А-Яа-яA-Za-z]{1,30}";
    private static final String LAST_NAME_REGEX = "[А-Яа-яA-Za-z]{1,50}";

    private ParameterValidator() {
    }

    public static boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }

    public static boolean isValidUsername(String username) {
        return username != null;
    }

    public static boolean isValidEmail(String email) {
        return email != null && Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPassword(String password, String repeatedPassword) {
        return password != null && password.equals(repeatedPassword) && Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean isValidFirstName(String firstName) {
        return firstName != null && Pattern.matches(FIRST_NAME_REGEX, firstName);
    }

    public static boolean isValidLastName(String lastName) {
        return lastName != null && Pattern.matches(LAST_NAME_REGEX, lastName);
    }
}
