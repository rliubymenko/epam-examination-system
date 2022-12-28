package com.epam.examinationsystem.core.enumeration;

public enum DaoConstant {

    ANSWER_TABLE_NAME("answer"),
    QUESTION_TABLE_NAME("question"),
    ROLE_TABLE_NAME("role"),
    SUBJECT_TABLE_NAME("subject"),
    TEST_TABLE_NAME("test"),
    USER_TABLE_NAME("user"),
    USER_TEST_TABLE_NAME("user_test");

    private final String value;

    DaoConstant(String field) {
        this.value = field;
    }

    public String getValue() {
        return value;
    }
}
