package com.epam.di.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class ObjectProvider {

    private static final Logger LOG = LoggerFactory.getLogger(ObjectProvider.class);
    private static DependencyInjectionContext context;

    private ObjectProvider() {
    }

    public static <I> I getInstance(Class<I> clazz) {
        String message = MessageFormat.format("Providing the instance for {0}", clazz.getSimpleName());
        LOG.debug(message);
        return context.getInstance(clazz);
    }

    static void setContext(DependencyInjectionContext context) {
        ObjectProvider.context = context;
    }
}
