package com.epam.di.environment;

import com.epam.di.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public final class PropertyResolver {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyResolver.class);

    private static PropertyResolver propertyResolver;
    private Map<String, String> propertiesMap;

    private PropertyResolver() {
    }

    public static PropertyResolver getInstance() {
        if (propertyResolver == null) {
            propertyResolver = new PropertyResolver();
            LOG.debug("Property Resolver was created");
        }
        return propertyResolver;
    }

    public void initializeInstance(ClassLoader classLoader) {
        propertiesMap = ResourceUtil.getProperties(classLoader);
        LOG.debug("Properties has been received");
    }

    public String getProperty(String key) {
        return propertiesMap.get(key);
    }
}
