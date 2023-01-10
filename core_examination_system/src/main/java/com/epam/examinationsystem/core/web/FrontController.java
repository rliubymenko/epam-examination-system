package com.epam.examinationsystem.core.web;

import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.factory.ActionFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/registration", "/login", "/logout", "/locale", "/home", "/admins/*", "/students/*"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory actionFactory = ActionFactory.getInstance();
        ActionCommand command = actionFactory.defineCommand(request);
        CommandResult result = command.execute(request, response);
        String page;
        if (result.getPage() != null) {
            page = result.getPage();
            if (result.isRedirect()) {
                String contextPath = request.getContextPath();
                if (page.contains(contextPath)) {
                    response.sendRedirect(page);
                } else {
                    response.sendRedirect(contextPath + page);
                }
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } else {
            page = Path.HOME;
            response.sendRedirect(page);
        }
    }
}
