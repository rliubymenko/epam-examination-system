package com.epam.di.configurator.impl;

import com.epam.di.annotation.PleaseValue;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.environment.PropertyResolver;
import com.epam.di.exception.AnnotationConfiguratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class ValueAnnotationConfigurator implements AnnotationConfigurator {

    private static final Logger LOG = LoggerFactory.getLogger(ValueAnnotationConfigurator.class);
    private static final PropertyResolver propertyResolver = PropertyResolver.getInstance();

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        String configuredClassName = candidateForInject.getClass().getSimpleName();
        String message = MessageFormat.format("Configuration of the {0} for injection values has been started", configuredClassName);
        LOG.debug(message);
        Field[] declaredFields = candidateForInject.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(PleaseValue.class)) {
                PleaseValue properties = declaredField.getAnnotation(PleaseValue.class);
                String propertyName = properties.value();
                String property = propertyResolver.getProperty(propertyName);
                if (!property.isBlank()) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(candidateForInject, property);
                        message = MessageFormat.format(
                                "The {0} value was injected into the {1} within {2} class",
                                property, declaredField.getName(), configuredClassName);
                        LOG.debug(message);
                    } catch (IllegalAccessException e) {
                        message = MessageFormat.format(
                                "Exception occurred during injecting value {0} into {1} within {2} class",
                                property, declaredField.getName(), configuredClassName);
                        LOG.error(message);
                        throw new AnnotationConfiguratorException(message);
                    }
                }
            }
        }
    }
}
