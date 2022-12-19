package com.epam.di.configurator;

import com.epam.di.DependencyInjectionContext;

public interface AnnotationConfigurator {

    void configure(Object candidateForInject, DependencyInjectionContext context);
}
