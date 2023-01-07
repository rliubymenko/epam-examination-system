<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<header>
    <nav class="navbar navbar-expand-lg navbar-light gradient-navbar">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <img
                        class="m-0 p-0"
                        src="${pageContext.request.contextPath}/static/img/logo1.webp"
                        height="80"
                        alt="Logo"
                        loading="lazy"
                />
                <small><fmt:message key="home.logo_name"/></small>
            </a>

            <div class="d-flex align-items-center">
                <a
                        class="btn btn-link px-3 me-2"
                        href="${pageContext.request.contextPath}/login"
                        role="button"
                ><fmt:message key="home.login"/></a>
                <a
                        class="btn btn-primary me-3"
                        href="${pageContext.request.contextPath}/registration"
                        role="button"
                ><fmt:message key="home.signup"/></a>

                <ul class="navbar-nav">
                    <!-- Language dropdown -->
                    <li class="nav-item dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="languageNavBar"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                        >
                            <i class="fas fa-globe m-0"></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/locale?lang=en_US"><i
                                        class="flag-america flag"></i><fmt:message key="home.en"/></a>

                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/locale?lang=uk_UA"><i
                                        class="flag-ukraine flag"></i><fmt:message key="home.ua"/></a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

