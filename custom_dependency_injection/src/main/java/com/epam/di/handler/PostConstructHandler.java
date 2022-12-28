package com.epam.di.handler;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.exception.DependencyInjectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

public class PostConstructHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PostConstructHandler.class);
    private final Map<Class<?>, Map<Class<?>, Object>> interfaceAndImplementationWithInstanceMap;

    public PostConstructHandler(Map<Class<?>, Map<Class<?>, Object>> interfaceAndImplementationWithInstanceMap) {
        this.interfaceAndImplementationWithInstanceMap = interfaceAndImplementationWithInstanceMap;
    }

    public void handle() {
        LOG.debug("Invoking class methods with @PleasePostConstruct annotation has been started");
        interfaceAndImplementationWithInstanceMap
                .values()
                .stream()
                .flatMap(implementationWithInstanceMap -> implementationWithInstanceMap.values().stream())
                .filter(Objects::nonNull)
                .forEach(instance -> {
                    Method method = findVoidMethodWithZeroParamsAndExistPleasePostConstructAnnotation(instance.getClass());
                    invokeSuitableMethod(instance, method);
                });
    }

    private Method findVoidMethodWithZeroParamsAndExistPleasePostConstructAnnotation(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterCount() == 0 && method.getReturnType() == void.class && method.isAnnotationPresent(PleasePostConstruct.class)) {
                String message = MessageFormat.format("Method inside {0} with @PleasePostConstruct annotation were found", clazz.getSimpleName());
                LOG.debug(message);
                return method;
            }
        }
        return null;
    }

    private void invokeSuitableMethod(Object instance, Method method) {
        String message;
        if (method != null) {
            try {
                method.setAccessible(true);
                method.invoke(instance);
                message = MessageFormat.format(
                        "Method inside {0} with @PleasePostConstruct annotation has been invoked",
                        instance.getClass().getSimpleName()
                );
                LOG.debug(message);
            } catch (IllegalAccessException | InvocationTargetException e) {
                message = MessageFormat.format(
                        "Method inside {0} with @PleasePostConstruct annotation caused following exception: ", e.getLocalizedMessage()
                );
                LOG.error(message);
                throw new DependencyInjectionException(message, e);
            }
        }
    }
}
