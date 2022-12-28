package com.epam.di.factory;

import com.epam.di.DependencyInjectionApplication;
import com.epam.di.configurator.AnnotationConfigurator;
import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.exception.DependencyInjectionException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanFactory {

    private static final Logger LOG = LoggerFactory.getLogger(BeanFactory.class);
    private final DependencyInjectionContext context;
    private final List<AnnotationConfigurator> annotationConfigurators = new ArrayList<>();

    public BeanFactory(DependencyInjectionContext context) {
        this.context = context;
        initAnnotationConfigurators();
    }

    public <I> I createInstance(Class<I> implementation) {
        String message = MessageFormat.format("Trying creating object instance from class {0}", implementation.getSimpleName());
        LOG.debug(message);
        I instance = create(implementation);
        configure(instance);
        message = MessageFormat.format("Configured object instance from class {0}", implementation.getSimpleName());
        LOG.debug(message);
        return instance;
    }

    private void initAnnotationConfigurators() {
        LOG.debug("Searching the corresponding implementation of AnnotationConfigurator interface");
        Reflections reflections = new Reflections(DependencyInjectionApplication.class.getPackageName());
        Set<Class<? extends AnnotationConfigurator>> subTypesOfAnnotationConfigurator = reflections.getSubTypesOf(AnnotationConfigurator.class);
        for (Class<? extends AnnotationConfigurator> subType : subTypesOfAnnotationConfigurator) {
            try {
                annotationConfigurators.add(subType.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                String message = "Error occurred during searching implementation of AnnotationConfigurator interface";
                LOG.error(message);
                throw new DependencyInjectionException(message, e);
            }
        }
    }

    private <I> I create(Class<I> implementation) {
        try {
            Constructor<I> constructor = implementation.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            String message = MessageFormat.format("Cannot instantiate of class {0}", implementation.getSimpleName());
            LOG.error(message);
            throw new DependencyInjectionException(message, e);
        }
    }

    private <I> void configure(I implementation) {
        annotationConfigurators.forEach(configurator -> configurator.configure(implementation, context));
    }
}
