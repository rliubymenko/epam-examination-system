package com.epam.examinationsystem.core.enumeration;

public enum UserType {
    ADMIN, STUDENT, TUTOR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
