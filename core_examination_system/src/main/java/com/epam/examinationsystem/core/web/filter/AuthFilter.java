package com.epam.examinationsystem.core.web.filter;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.RootPath;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthFilter.class);
    private static final String ADMIN = "admin";
    private static final String STUDENT = "student";
    private static final String HOME_PATH = RootPath.CONTEXT_PATH + Path.HOME;
    private static final Set<String> userPaths = Set.of(
            RootPath.HOME,
            RootPath.LOCALE,
            RootPath.LOGOUT,
            RootPath.STATIC
    );

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        LOG.debug("Auth filter has been started ...");
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        String requestPath = request.getRequestURI();
        String path = getPathWithoutContext(requestPath);
        LOG.debug("Current path is {}", requestPath);
        Boolean isLoggedIn = (Boolean) request.getSession().getAttribute(SessionConstant.IS_LOGGED_IN);
        if (BooleanUtils.toBoolean(isLoggedIn)) {
            UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
            LOG.debug("Current user is {}", currentUser);
            boolean validPath = checkPathContainingForUsers(path);
            if (currentUser.getRole().equals(ADMIN) &&
                    (path.startsWith(RootPath.ADMINS) || validPath) &&
                    !(path.equals(RootPath.ADMINS) || path.equals("/admins/"))) {
                LOG.debug("Filtered admin");
                chain.doFilter(servletRequest, servletResponse);
            } else if (currentUser.getRole().equals(STUDENT) &&
                    (path.startsWith(RootPath.STUDENTS) || validPath) &&
                    !(path.equals(RootPath.STUDENTS) || path.equals("/students/"))) {
                LOG.debug("Filtered student");
                chain.doFilter(servletRequest, servletResponse);
            } else {
                LOG.debug("Filtered neither admin nor student and that's why go home");
                response.sendRedirect(HOME_PATH);
            }
        } else if (!checkPathForCommonResources(path)) {
            LOG.debug("Redirect to home page because the current common queried page does not exist");
            response.sendRedirect(HOME_PATH);
        } else {
            LOG.debug("Redirect to the current common queried page");
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    private String getPathWithoutContext(String path) {
        return path.replace(RootPath.CONTEXT_PATH, "");
    }

    private boolean checkPathContainingForUsers(String path) {
        return userPaths
                .stream()
                .anyMatch(path::startsWith);
    }

    private boolean checkPathForCommonResources(String path) {
        return path.equals(RootPath.HOME) || path.equals(RootPath.HOME + "/") ||
                path.equals(RootPath.LOGIN) || path.equals(RootPath.LOGIN + "/") ||
                path.equals(RootPath.REGISTRATION) || path.equals(RootPath.REGISTRATION + "/") ||
                path.equals(RootPath.LOCALE) || path.equals(RootPath.LOGOUT) ||
                path.equals(RootPath.CONTEXT_PATH) || path.equals(RootPath.ROOT) ||
                path.startsWith(RootPath.STATIC);
    }
}
