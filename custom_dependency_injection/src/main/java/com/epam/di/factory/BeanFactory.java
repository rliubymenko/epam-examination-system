package com.epam.di.factory;

import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.configurator.AnnotationConfigurator;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanFactory {

    private final DependencyInjectionContext context;
    private final List<AnnotationConfigurator> annotationConfigurators = new ArrayList<>();

    public BeanFactory(DependencyInjectionContext context) {
        this.context = context;
        initAnnotationConfigurators();
    }

    public <I> I createInstance(Class<I> implementation) {
        I instance = create(implementation);
        configure(instance);
        return instance;
    }

    private void initAnnotationConfigurators() {
        Reflections reflections = new Reflections(context.getClass());
        Set<Class<? extends AnnotationConfigurator>> subTypesOfAnnotationConfigurator = reflections.getSubTypesOf(AnnotationConfigurator.class);
        for (Class<? extends AnnotationConfigurator> subType : subTypesOfAnnotationConfigurator) {
            try {
                annotationConfigurators.add(subType.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                throw new RuntimeException("An error occurred during object configuration " + e.getMessage());
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
            throw new RuntimeException("Cannot create object " + e.getMessage());
        }
    }

    private <I> void configure(I implementation) {
        annotationConfigurators.forEach(configurator -> configurator.configure(implementation, context));
    }
}
