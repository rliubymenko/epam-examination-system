package com.epam.di;

import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.environment.PropertyResolver;
import com.epam.di.factory.BeanFactory;

public class DependencyInjectionApplication {

    private static DependencyInjectionContext applicationContext;

    private DependencyInjectionApplication() {
    }

    public static void run(Class<?> configClass) {
        PropertyResolver.getInstance().initializeInstance(configClass.getClassLoader());
        applicationContext = new DependencyInjectionContext(configClass);
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        applicationContext.applicationInitialization();
    }

    public static DependencyInjectionContext getConfiguredContext() {
        return applicationContext;
    }
}
