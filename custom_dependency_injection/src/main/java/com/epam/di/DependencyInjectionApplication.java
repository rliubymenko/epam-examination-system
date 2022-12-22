package com.epam.di;

import com.epam.di.context.DependencyInjectionContext;
import com.epam.di.factory.BeanFactory;

public class DependencyInjectionApplication {

    private DependencyInjectionApplication() {
    }

    public static void run(Class<?> configClass) {
        DependencyInjectionContext applicationContext = new DependencyInjectionContext(configClass);
        BeanFactory beanFactory = new BeanFactory(applicationContext);
        applicationContext.setBeanFactory(beanFactory);
        applicationContext.applicationInitialization();
    }
}
