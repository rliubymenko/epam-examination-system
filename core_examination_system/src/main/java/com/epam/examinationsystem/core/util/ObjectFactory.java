package com.epam.examinationsystem.core.util;

import com.epam.di.context.DependencyInjectionContext;

public class ObjectFactory {

    private ObjectFactory() {
    }

    private static DependencyInjectionContext context;

    public static <I> I getInstance(Class<I> clazz) {
        return context.getInstance(clazz);
    }

    public static void setContext(DependencyInjectionContext context) {
        ObjectFactory.context = context;
    }
}
