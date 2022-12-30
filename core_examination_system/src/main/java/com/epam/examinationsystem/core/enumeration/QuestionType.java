package com.epam.examinationsystem.core.enumeration;

public enum QuestionType {
    MULTIPLE_CHOICE,
    SINGLE_CHOICE,
    TRUE_FALSE,
    NUMERICAL,
    TEXT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
