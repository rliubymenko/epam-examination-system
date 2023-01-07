package com.epam.examinationsystem.core.web.command;

import com.epam.examinationsystem.core.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ActionCommand {

    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
