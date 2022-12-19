package com.epam.di.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassLoaderUtil {

    private ClassLoaderUtil() {
    }

    public static List<Class<?>> getClasses(String packageName, ClassLoader classLoader) throws IOException {
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
            throw new RuntimeException("Class not found" + e.getLocalizedMessage());
        }
    }
}
