package com.epam.di.configurator.impl;

import com.epam.di.annotation.PleaseConnectionManager;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.exception.AnnotationConfiguratorException;

import java.lang.reflect.Field;

public class ConnectionManagerConfigurator implements AnnotationConfigurator {

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        Field[] fields = candidateForInject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PleaseConnectionManager.class)) {
                field.setAccessible(true);
                try {
                    field.set(candidateForInject, ConnectionPoolManager.INSTANCE);
                } catch (IllegalAccessException e) {
                    throw new AnnotationConfiguratorException(field.getName(), candidateForInject.getClass().getSimpleName());
                }
            }
        }
    }
}
