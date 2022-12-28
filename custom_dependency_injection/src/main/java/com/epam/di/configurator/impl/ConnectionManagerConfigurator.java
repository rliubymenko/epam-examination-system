package com.epam.di.configurator.impl;

import com.epam.di.annotation.PleaseConnectionManager;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.exception.AnnotationConfiguratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class ConnectionManagerConfigurator implements AnnotationConfigurator {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionManagerConfigurator.class);

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        String configuredClassName = candidateForInject.getClass().getSimpleName();
        String message = MessageFormat.format("Configuration of the {0} for injection of connection pool manager instance has been started", configuredClassName);
        LOG.debug(message);
        Field[] fields = candidateForInject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PleaseConnectionManager.class)) {
                field.setAccessible(true);
                try {
                    field.set(candidateForInject, ConnectionPoolManager.INSTANCE);
                    message = MessageFormat.format("Connection pool manager instance was injected into the {0}", configuredClassName);
                    LOG.debug(message);
                } catch (IllegalAccessException e) {
                    message = MessageFormat.format("Exception occurred during injecting field {0} into {1}", field.getType().getSimpleName(), configuredClassName);
                    LOG.error(message);
                    throw new AnnotationConfiguratorException(message);
                }
            }
        }
    }
}
