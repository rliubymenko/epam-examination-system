package com.epam.di.configurator;

import com.epam.di.context.DependencyInjectionContext;

public interface AnnotationConfigurator {

    void configure(Object candidateForInject, DependencyInjectionContext context);
}
