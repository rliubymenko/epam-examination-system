package com.epam.di.configurator.impl;

import com.epam.di.DependencyInjectionContext;
import com.epam.di.annotation.PleaseValue;
import com.epam.di.configurator.AnnotationConfigurator;

import java.lang.reflect.Field;

public class ValueAnnotationConfigurator implements AnnotationConfigurator {

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        Field[] declaredFields = candidateForInject.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(PleaseValue.class)) {
                PleaseValue properties = declaredField.getAnnotation(PleaseValue.class);
                String propertyName = properties.value();
                String property = context.getPropertiesMap().get(propertyName);
                if (!property.isBlank()) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(candidateForInject, property);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Exception while injecting property " + propertyName + " into " + candidateForInject.getClass().getSimpleName());
                    }
                }
            }
        }
    }
}
