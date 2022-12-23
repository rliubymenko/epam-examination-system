package com.epam.di.configurator.impl;

import com.epam.di.annotation.PleaseValue;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.environment.PropertyResolver;
import com.epam.di.exception.AnnotationConfiguratorException;

import java.lang.reflect.Field;

public class ValueAnnotationConfigurator implements AnnotationConfigurator {

    private static final PropertyResolver propertyResolver = PropertyResolver.getInstance();

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
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
                    } catch (IllegalAccessException e) {
                        throw new AnnotationConfiguratorException(propertyName, candidateForInject.getClass().getSimpleName());
                    }
                }
            }
        }
    }
}
