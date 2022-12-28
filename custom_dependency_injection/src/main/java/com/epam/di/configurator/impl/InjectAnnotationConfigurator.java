package com.epam.di.configurator.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.exception.AnnotationConfiguratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class InjectAnnotationConfigurator implements AnnotationConfigurator {

    private static final Logger LOG = LoggerFactory.getLogger(InjectAnnotationConfigurator.class);

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        String configuredClassName = candidateForInject.getClass().getSimpleName();
        String message = MessageFormat.format("Configuration of the {0} for injection has been started", configuredClassName);
        LOG.debug(message);
        Field[] fields = candidateForInject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PleaseInject.class)) {
                field.setAccessible(true);
                try {
                    field.set(candidateForInject, context.getInstance(field.getType()));
                    message = MessageFormat.format("The {0} instance was injected into the {1}", field.getType().getSimpleName(), configuredClassName);
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
