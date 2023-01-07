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
        commands.put(CommandType.POST_REGISTRATION, ObjectProvider.getInstance(RegistrationCommand.class));
        commands.put(CommandType.POST_LOGIN, ObjectProvider.getInstance(LoginCommand.class));

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
        String methodType = request.getMethod();
        if (methodType.equals(Path.GET_METHOD)) {
            command = "GET_" + path;
        }
        if (methodType.equals(Path.POST_METHOD)) {
            command = "POST_" + path;
        }
        CommandType commandType = CommandType.valueOf(command.toUpperCase());
        return commands.getOrDefault(commandType, new EmptyCommand());
    }
}
