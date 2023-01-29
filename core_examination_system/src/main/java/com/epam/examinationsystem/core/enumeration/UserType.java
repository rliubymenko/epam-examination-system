package com.epam.examinationsystem.core.enumeration;

public enum UserType {
    ADMIN, STUDENT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
