package com.epam.di.configurator;

import com.epam.di.DependencyInjectionContext;
import com.epam.di.annotation.PleaseInject;

import java.lang.reflect.Field;

public class InjectAnnotationConfigurator implements AnnotationConfigurator {

    @Override
    public void configure(Object candidateForInject, DependencyInjectionContext context) {
        Field[] fields = candidateForInject.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PleaseInject.class)) {
                field.setAccessible(true);
                try {
                    field.set(candidateForInject, context.getInstance(field.getType()));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Exception while injecting field " + field.getName() + " into " + candidateForInject.getClass().getSimpleName());
                }
            }
        }
    }
}
