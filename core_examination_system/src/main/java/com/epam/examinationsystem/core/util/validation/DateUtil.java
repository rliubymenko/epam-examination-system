package com.epam.examinationsystem.core.util.validation;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public final class DateUtil {

    private DateUtil() {
    }

    public static LocalDateTime parseDateTime(String dateString) {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static String compareDateForStudent(LocalDateTime dateOfExpiration) {
        if (dateOfExpiration == null) {
            return "true";
        }
        LocalDateTime now = LocalDateTime.now();
        return Boolean.toString(dateOfExpiration.isAfter(now));
    }
}
