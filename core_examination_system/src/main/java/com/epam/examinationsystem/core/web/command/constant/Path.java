package com.epam.examinationsystem.core.web.command.constant;

public interface Path {

    String ROOT = "/";
    String HOME = "/home";
    String REGISTRATION = "/registration";
    String LOGIN = "/login";
    String USERS = "/admins/users";
    String SUBJECTS = "/admins/subjects";
    String TESTS = "/admins/tests";
    String QUESTIONS = "/admins/questions";
    String ANSWERS = "/admins/answers";


    String POST_METHOD = "POST";
    String GET_METHOD = "GET";


    String HOME_PAGE = "/view/shared/home.jsp";
    String LOGIN_PAGE = "/view/shared/login.jsp";
    String REGISTRATION_PAGE = "/view/shared/registration.jsp";
    String USERS_PAGE = "/view/admin/users.jsp";
    String ADMIN_ACCOUNT_PAGE = "/view/admin/account.jsp";
    String EDIT_USER_PAGE = "/view/admin/editUser.jsp";
    String SUBJECTS_PAGE = "/view/admin/subjects.jsp";
    String EDIT_SUBJECT_PAGE = "/view/admin/editSubject.jsp";
    String NEW_SUBJECT_PAGE = "/view/admin/newSubject.jsp";
    String TESTS_PAGE = "/view/admin/tests.jsp";
    String EDIT_TEST_PAGE = "/view/admin/editTest.jsp";
    String NEW_TEST_PAGE = "/view/admin/newTest.jsp";

    String QUESTIONS_PAGE = "/view/admin/questions.jsp";
    String EDIT_QUESTION_PAGE = "/view/admin/editQuestion.jsp";
    String NEW_QUESTION_PAGE = "/view/admin/newQuestion.jsp";

    String ANSWERS_PAGE = "/view/admin/answers.jsp";
    String EDIT_ANSWERS_PAGE = "/view/admin/editAnswer.jsp";
    String SINGLE_CHOICE_DELETE_ANSWER_PAGE = "/view/admin/deleteAnswer.jsp";
    String NEW_ANSWER_PAGE = "/view/admin/newAnswer.jsp";


    String LANGUAGE = "lang";
    String CURRENT_PAGE = "referer";
}
