package com.epam.examinationsystem.core.web.command.enumeration;

public enum CommandType {
    GET_HOME,
    GET_LOCALE,
    GET_REGISTRATION,
    GET_PASSWORDCHANGE,
    GET_LOGIN,
    GET_LOGOUT,
    GET_ADMINS_USERS,
    GET_ADMINS_USERS_RESET_PASSWORD,
    GET_ADMINS_USERS_USER,
    GET_ADMINS_SUBJECTS,
    GET_ADMINS_SUBJECTS_SUBJECT,
    GET_ADMINS_SUBJECTS_SUBJECT_NEW,
    GET_ADMINS_SUBJECTS_SUBJECT_DELETE,
    GET_ADMINS_TESTS,
    GET_ADMINS_TESTS_TEST,
    GET_ADMINS_TESTS_TEST_NEW,
    GET_ADMINS_TESTS_TEST_DELETE,
    GET_ADMINS_QUESTIONS,
    GET_ADMINS_QUESTIONS_QUESTION,
    GET_ADMINS_QUESTIONS_QUESTION_NEW,
    GET_ADMINS_QUESTIONS_QUESTION_DELETE,
    GET_ADMINS_ANSWERS,
    GET_ADMINS_ANSWERS_ANSWER,
    GET_ADMINS_ANSWERS_ANSWER_NEW,
    GET_ADMINS_ANSWERS_ANSWER_DELETE,

    GET_ADMINS_USERS_REPORT,
    GET_ADMINS_ANSWERS_REPORT,
    GET_ADMINS_QUESTIONS_REPORT,
    GET_ADMINS_SUBJECTS_REPORT,
    GET_ADMINS_TESTS_REPORT,
    GET_ADMINS_USERTESTS_REPORT,

    GET_STUDENTS_SUBJECTS,
    GET_STUDENTS_TESTS,
    GET_STUDENTS_TESTS_TESTING,
    GET_STUDENTS_STUDENT,
    GET_STUDENTS_ACCOUNT_RESET_PASSWORD,

    GET_ADMINS_USERTESTS,

    GET_ADMINS_ACCOUNT,
    GET_STUDENTS_ACCOUNT,
    GET_STUDENTS_TESTS_TEST_SELECT,

    POST_LOGIN,
    POST_REGISTRATION,
    POST_PASSWORDCHANGE,
    POST_ADMINS_USERS_USER,

    POST_ADMINS_SUBJECTS_SUBJECT,
    POST_ADMINS_SUBJECTS_SUBJECT_NEW,

    POST_ADMINS_TESTS_TEST,
    POST_ADMINS_TESTS_TEST_NEW,

    POST_ADMINS_QUESTIONS_QUESTION,
    POST_ADMINS_QUESTIONS_QUESTION_NEW,

    POST_ADMINS_ANSWERS_ANSWER,
    POST_ADMINS_ANSWERS_ANSWER_NEW,
    POST_ADMINS_ANSWERS_ANSWER_DELETE,

    POST_STUDENTS_STUDENT,
    POST_STUDENTS_TESTS_TESTING
}
