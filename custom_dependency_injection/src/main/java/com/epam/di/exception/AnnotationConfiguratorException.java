package com.epam.di.exception;

public class AnnotationConfiguratorException extends RuntimeException {

    public AnnotationConfiguratorException(String fieldName, String objectName) {
        super("Exception while injecting field " + fieldName + " into " + objectName);
    }
}
