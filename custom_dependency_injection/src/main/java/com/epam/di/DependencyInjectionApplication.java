package com.epam.di;

import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.environment.PropertyResolver;
import com.epam.di.factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DependencyInjectionApplication {

    private static final Logger LOG = LoggerFactory.getLogger(DependencyInjectionApplication.class);

    private DependencyInjectionApplication() {
    }

    public static void run(Class<?> configClass) {
        LOG.debug("Custom dependency injection container setup has been started");
        PropertyResolver.getInstance().initializeInstance(configClass.getClassLoader());
        DependencyInjectionContext context = new DependencyInjectionContext(configClass);
        BeanFactory beanFactory = new BeanFactory(context);
        context.setBeanFactory(beanFactory);
        context.applicationInitialization();
        LOG.debug("Custom dependency injection container setup has been ended");
    }
}
