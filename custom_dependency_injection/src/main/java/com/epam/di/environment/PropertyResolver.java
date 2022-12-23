package com.epam.di.environment;

import com.epam.di.util.ResourceUtil;

import java.util.Map;

public final class PropertyResolver {

    private static PropertyResolver propertyResolver;
    private Map<String, String> propertiesMap;

    private PropertyResolver() {
    }

    public static PropertyResolver getInstance() {
        if (propertyResolver == null) {
            propertyResolver = new PropertyResolver();
        }
        return propertyResolver;
    }

    public void initializeInstance(ClassLoader classLoader) {
        propertiesMap = ResourceUtil.getProperties(classLoader);
    }

    public String getProperty(String key) {
        return propertiesMap.get(key);
    }
}
