package com.epam.di.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassLoaderUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ClassLoaderUtil.class);

    private ClassLoaderUtil() {
    }

    public static Set<Class<?>> getClassesAnnotatedWith(String packageName, ClassLoader classLoader, Class<? extends Annotation> annotation) {
        String message = MessageFormat.format("Starting of the search for suitable classes marked with {0} annotation", annotation.getSimpleName());
        LOG.debug(message);
        try {
            List<Class<?>> classes = getClasses(packageName, classLoader);
            return classes.stream()
                    .filter(clazz -> clazz.isAnnotationPresent(annotation))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            message = "Error during look up classes";
            LOG.error(message);
            throw new RuntimeException(message, e);
        }
    }

    private static List<Class<?>> getClasses(String packageName, ClassLoader classLoader) throws IOException {
        List<File> directories = new ArrayList<>();
        List<Class<?>> classes = new ArrayList<>();
        String correctedPath = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(correctedPath);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            directories.add(new File(resource.getFile()));
        }
        for (File directory : directories) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(getClass(file.getName(), packageName));
            }
        }
        return classes;
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            String message = MessageFormat.format("Class {0} not found", className);
            LOG.error(message);
            throw new RuntimeException(message, e);
        }
    }
}
