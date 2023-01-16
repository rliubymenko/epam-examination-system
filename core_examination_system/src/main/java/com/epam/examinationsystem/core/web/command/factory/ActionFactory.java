package com.epam.examinationsystem.core.web.command.factory;

import com.epam.di.context.ObjectProvider;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.enumeration.CommandType;
import com.epam.examinationsystem.core.web.command.impl.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.EnumMap;

public class ActionFactory {

    private static ActionFactory actionFactory;
    private final EnumMap<CommandType, ActionCommand> commands = new EnumMap<>(CommandType.class);

    private ActionFactory() {
        commands.put(CommandType.GET_HOME, ObjectProvider.getInstance(HomeCommand.class));
        commands.put(CommandType.GET_LOCALE, ObjectProvider.getInstance(LocaleCommand.class));
        commands.put(CommandType.GET_REGISTRATION, ObjectProvider.getInstance(GetRegistrationPageCommand.class));
        commands.put(CommandType.GET_LOGIN, ObjectProvider.getInstance(GetLoginPageCommand.class));
        commands.put(CommandType.GET_LOGOUT, ObjectProvider.getInstance(LogoutCommand.class));

        commands.put(CommandType.GET_ADMINS_USERS, ObjectProvider.getInstance(GetAllUsersByParametersCommand.class));
        commands.put(CommandType.GET_ADMINS_USERS_USER, ObjectProvider.getInstance(GetEditUserPageCommand.class));

        commands.put(CommandType.GET_ADMINS_SUBJECTS, ObjectProvider.getInstance(GetAllSubjectsByParametersCommand.class));
        commands.put(CommandType.GET_ADMINS_SUBJECTS_SUBJECT, ObjectProvider.getInstance(GetEditSubjectPageCommand.class));
        commands.put(CommandType.GET_ADMINS_SUBJECTS_SUBJECT_NEW, ObjectProvider.getInstance(GetNewSubjectPageCommand.class));
        commands.put(CommandType.GET_ADMINS_SUBJECTS_SUBJECT_DELETE, ObjectProvider.getInstance(DeleteSubjectPageCommand.class));

        commands.put(CommandType.GET_ADMINS_TESTS, ObjectProvider.getInstance(GetAllTestsByParametersCommand.class));
        commands.put(CommandType.GET_ADMINS_TESTS_TEST, ObjectProvider.getInstance(GetEditTestPageCommand.class));
        commands.put(CommandType.GET_ADMINS_TESTS_TEST_NEW, ObjectProvider.getInstance(GetNewTestPageCommand.class));
        commands.put(CommandType.GET_ADMINS_TESTS_TEST_DELETE, ObjectProvider.getInstance(DeleteTestPageCommand.class));

        commands.put(CommandType.GET_ADMINS_QUESTIONS, ObjectProvider.getInstance(GetAllQuestionsByParametersCommand.class));
        commands.put(CommandType.GET_ADMINS_QUESTIONS_QUESTION, ObjectProvider.getInstance(GetEditQuestionPageCommand.class));
        commands.put(CommandType.GET_ADMINS_QUESTIONS_QUESTION_NEW, ObjectProvider.getInstance(GetNewQuestionPageForTestCommand.class));
        commands.put(CommandType.GET_ADMINS_QUESTIONS_QUESTION_DELETE, ObjectProvider.getInstance(DeleteQuestionPageCommand.class));

        commands.put(CommandType.GET_ADMINS_ANSWERS, ObjectProvider.getInstance(GetAllAnswersByParametersCommand.class));
        commands.put(CommandType.GET_ADMINS_ANSWERS_ANSWER, ObjectProvider.getInstance(GetEditAnswerPageCommand.class));
        commands.put(CommandType.GET_ADMINS_ANSWERS_ANSWER_NEW, ObjectProvider.getInstance(GetNewAnswerPageCommand.class));
        commands.put(CommandType.GET_ADMINS_ANSWERS_ANSWER_DELETE, ObjectProvider.getInstance(DeleteAnswerPageCommand.class));

        commands.put(CommandType.GET_ADMINS_ACCOUNT, ObjectProvider.getInstance(GetAdminAccountPageCommand.class));

        commands.put(CommandType.POST_REGISTRATION, ObjectProvider.getInstance(RegistrationCommand.class));
        commands.put(CommandType.POST_LOGIN, ObjectProvider.getInstance(LoginCommand.class));
        commands.put(CommandType.POST_ADMINS_USERS_USER, ObjectProvider.getInstance(EditUserCommand.class));

        commands.put(CommandType.POST_ADMINS_SUBJECTS_SUBJECT, ObjectProvider.getInstance(EditSubjectCommand.class));
        commands.put(CommandType.POST_ADMINS_SUBJECTS_SUBJECT_NEW, ObjectProvider.getInstance(CreateSubjectCommand.class));

        commands.put(CommandType.POST_ADMINS_TESTS_TEST, ObjectProvider.getInstance(EditTestCommand.class));
        commands.put(CommandType.POST_ADMINS_TESTS_TEST_NEW, ObjectProvider.getInstance(CreateTestCommand.class));

        commands.put(CommandType.POST_ADMINS_QUESTIONS_QUESTION, ObjectProvider.getInstance(EditQuestionCommand.class));
        commands.put(CommandType.POST_ADMINS_QUESTIONS_QUESTION_NEW, ObjectProvider.getInstance(CreateQuestionForTestCommand.class));

        commands.put(CommandType.POST_ADMINS_ANSWERS_ANSWER, ObjectProvider.getInstance(EditAnswerCommand.class));
        commands.put(CommandType.POST_ADMINS_ANSWERS_ANSWER_NEW, ObjectProvider.getInstance(CreateAnswerCommand.class));
        commands.put(CommandType.POST_ADMINS_ANSWERS_ANSWER_DELETE, ObjectProvider.getInstance(SingleChoiceDeleteAnswerCommand.class));
    }

    public static ActionFactory getInstance() {
        if (actionFactory == null) {
            actionFactory = new ActionFactory();
        }
        return actionFactory;
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        String command = "";
        String path = request.getServletPath().replace("/", "");
        String pathInfo = request.getPathInfo();
        String methodType = request.getMethod();
        if (pathInfo != null) {
            path += pathInfo.replace("/", "_");
        }
        if (methodType.equals(Path.GET_METHOD)) {
            command = "GET_" + path;
        }
        if (methodType.equals(Path.POST_METHOD)) {
            command = "POST_" + path;
        }
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return commands.get(commandType);
    }
}
