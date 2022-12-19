package com.epam.di.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {

    private ResourceUtil() {
    }

    public static Map<String, String> getProperties(ClassLoader classLoader) {
        try (InputStream resourceStream = classLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(resourceStream);
            Map<String, String> propertiesMap = new HashMap<>();
            for (Map.Entry<Object, Object> property : properties.entrySet()) {
                propertiesMap.put(
                        String.valueOf(property.getKey()),
                        String.valueOf(property.getValue())
                );
            }
            return propertiesMap;
        } catch (IOException exception) {
            throw new RuntimeException("File not found" + exception.getLocalizedMessage());
        }
    }
}
