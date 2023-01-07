<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html lang="en">
<head>
    <title>Error page</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/error.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/lib/magnific-popup.css">
    <script src="${pageContext.request.contextPath}/static/js/lib/jquery.magnific-popup.min.js" async></script>
    <script src="${pageContext.request.contextPath}/static/js/lib/google_iframe_lib.min.js" async></script>
    <script src="${pageContext.request.contextPath}/static/js/error.js" async></script>
</head>
<body>
<section class="notFound">
    <div class="img">
        <img src="${pageContext.request.contextPath}/static/img/backToTheHomePage.webp" alt="Back to the Homepage"/>
        <img src="${pageContext.request.contextPath}/static/img/delorean.webp" alt="El Delorean, El Doc y Marti McFly"/>
    </div>
    <div class="text">
        <c:set var="statusCode" scope="page" value="${pageContext.errorData.statusCode}"/>
        <h1>${statusCode}</h1>
        <c:choose>
            <c:when test="${statusCode eq 404}">
                <h2>
                    <fmt:message key="error.page_not_found"/>
                </h2>
            </c:when>
            <c:when test="${statusCode eq 500}">
                <h2>
                    <fmt:message key="error.internal_server_error"/>
                </h2>
            </c:when>
            <c:otherwise>
                <h2>
                    <fmt:message key="error.other_error"/>
                </h2>
            </c:otherwise>
        </c:choose>
        <h3><fmt:message key="error.back_to_home"/></h3>

        <div class="container">
            <div class="row">
                <div class="col-md">
                    <a href="${pageContext.request.contextPath}/home" class="yes">
                        <fmt:message key="error.yes"/>
                    </a>
                </div>
                <div class="col-md">
                    <c:if test="${sessionScope.locale eq null or sessionScope.locale eq 'en_US'}">
                        <a class="popup-youtube" href="https://www.youtube.com/watch?v=G3AfIvJBcGo">
                            <fmt:message key="error.no"/>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.locale eq 'uk_UA'}">
                        <a class="popup-youtube" href="https://www.youtube.com/watch?v=uSqLF7bPinY">
                            <fmt:message key="error.no"/>
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
