package com.epam.di.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceUtil.class);

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
        } catch (IOException e) {
            String message = "File with name application.properties not found";
            LOG.error(message);
            throw new RuntimeException(message, e);
        }
    }
}
