package com.epam.di.util;

import java.util.Map;
import java.util.stream.Collectors;

public class PropertyValidatorUtil {

    private PropertyValidatorUtil() {
    }

    public static String validate(Map<String, String> properties) {
        String errorMessage = properties.entrySet()
                .stream()
                .filter(property -> property.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.joining(", ", "the value ", " is nullable"),
                                value -> {
                                    if (value.equals("the value  is nullable")) return null;
                                    return value;
                                }
                        )
                );
        if (errorMessage != null) {
            return "Please update the application.properties file by adding the following properties. Details: " + errorMessage;
        }
        return null;
    }
}
