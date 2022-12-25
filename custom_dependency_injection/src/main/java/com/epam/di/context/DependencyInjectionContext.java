package com.epam.di.context;

import com.epam.di.annotation.PleaseComponentScan;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.di.factory.BeanFactory;
import com.epam.di.util.ClassLoaderUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DependencyInjectionContext {

    private final Map<Class<?>, Map<Class<?>, Object>> interfaceAndImplementationWithInstanceMap = new HashMap<>();
    private final Set<Class<?>> typesAnnotatedWithPleaseService;
    private BeanFactory beanFactory;

    public DependencyInjectionContext(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(PleaseComponentScan.class)) {
            throw new RuntimeException("Please specify the package for scan by adding @PleaseComponentScan");
        } else {
            ClassLoader mainClassLoader = configClass.getClassLoader();
            PleaseComponentScan componentScanAnnotation = configClass.getAnnotation(PleaseComponentScan.class);
            typesAnnotatedWithPleaseService = ClassLoaderUtil.getClassesAnnotatedWith(componentScanAnnotation.value(), mainClassLoader, PleaseService.class);
            for (Class<?> implementationClass : typesAnnotatedWithPleaseService) {
                Class<?>[] interfaces = implementationClass.getInterfaces();
                Map<Class<?>, Object> implementationWithInstance = new HashMap<>();
                implementationWithInstance.put(implementationClass, null);
                if (interfaces.length == 0) {
                    interfaceAndImplementationWithInstanceMap.put(implementationClass, implementationWithInstance);
                } else {
                    for (Class<?> iface : interfaces) {
                        interfaceAndImplementationWithInstanceMap.put(iface, implementationWithInstance);
                    }
                }
            }
        }
    }

    public void applicationInitialization() {
        for (Class<?> serviceClass : typesAnnotatedWithPleaseService) {
            getInstance(serviceClass);
        }
    }

    public <I> I getInstance(Class<I> type) {
        if (type.getSimpleName().equals("ConnectionPoolManager")) {
            return (I) ConnectionPoolManager.INSTANCE;
        }
        Class<? extends I> implementationClass;
        Map<Class<?>, Object> implementationWithInstanceMap = interfaceAndImplementationWithInstanceMap.get(type);
        if (type.isAnnotationPresent(PleaseService.class) || implementationWithInstanceMap != null) {
            if (implementationWithInstanceMap == null) {
                implementationWithInstanceMap = interfaceAndImplementationWithInstanceMap.values()
                        .stream()
                        .filter(implWithInst -> implWithInst.containsKey(type))
                        .findFirst()
                        .get();
            }
            implementationClass = (Class<? extends I>) implementationWithInstanceMap.keySet()
                    .iterator()
                    .next();
            if (implementationWithInstanceMap.get(implementationClass) != null) {
                return (I) implementationWithInstanceMap.get(implementationClass);
            }
            if (implementationClass != null) {
                I preparedInstance = beanFactory.createInstance(implementationClass);
                Map<Class<?>, Object> implementationWithInstance = new HashMap<>();
                implementationWithInstance.put(implementationClass, preparedInstance);
                if (type.isInterface()) {
                    interfaceAndImplementationWithInstanceMap.replace(type, implementationWithInstance);
                } else {
                    Class<?> keyOfClass = getKeyByOldValue(implementationWithInstanceMap);
                    interfaceAndImplementationWithInstanceMap.replace(keyOfClass, implementationWithInstance);
                }
                return preparedInstance;
            }
            throw new RuntimeException("No implementation found");
        }
        throw new RuntimeException("No @PleaseService annotation present or type does not exist in context");
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    private Class<?> getKeyByOldValue(Map<Class<?>, Object> implementationWithInstanceMap) {
        for (Map.Entry<Class<?>, Map<Class<?>, Object>> entry : interfaceAndImplementationWithInstanceMap.entrySet()) {
            if (entry.getValue().equals(implementationWithInstanceMap)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
