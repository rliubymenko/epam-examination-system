package com.epam.examinationsystem.core.enumeration;

public enum TestComplexity {
    EASY,
    MODERATE,
    HARD;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
