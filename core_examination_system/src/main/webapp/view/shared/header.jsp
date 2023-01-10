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

            <c:if test="${not empty is_logged_in and is_logged_in}">
                <ul class="navbar-nav me-auto d-flex flex-row">
                    <!-- Icon dropdown -->
                    <li class="nav-item me-3 me-lg-0 dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                        >
                            <i class="fas fa-user"></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:if test="${current_user.role == 'admin'}">
                                <li>
                                    <a class="dropdown-item" href="#">Personal account</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admins/users">
                                        <fmt:message key="home.users"/>
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admins/subjects">
                                        <fmt:message key="home.subjects"/>
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Tests</a>
                                </li>
                            </c:if>
                            <c:if test="${current_user.role == 'student'}">
                                <li>
                                    <a class="dropdown-item" href="#">Personal account</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Subjects</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Tests</a>
                                </li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </c:if>

            <div class="d-flex align-items-center">
                <c:if test="${empty is_logged_in or !is_logged_in}">
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
                </c:if>

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
                <c:if test="${not empty is_logged_in and is_logged_in}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                        <i id="logout" class="fa-lg fa-solid fa-arrow-right-from-bracket"></i>
                    </a>
                </c:if>
            </div>
        </div>
    </nav>
</header>

