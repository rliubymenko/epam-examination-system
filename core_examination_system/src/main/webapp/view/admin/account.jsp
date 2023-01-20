<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Admin account</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<section>
    <div class="container py-5">

        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <img src="${pageContext.request.contextPath}/static/img/ava.webp"
                             alt="avatar"
                             class="rounded-circle img-fluid" style="width: 150px;">
                        <h5 class="my-3">
                            ${current_user.username}
                        </h5>
                        <p class="text-muted mb-1">
                            <c:if test="${current_user.role == 'admin'}">
                                <fmt:message key="role.admin"/>
                            </c:if>
                            <c:if test="${current_user.isActivated}">
                                <span class="badge ms-2 badge-success rounded-pill d-inline">
                                  <fmt:message key="user.active"/>
                                </span>
                            </c:if>
                            <c:if test="${!current_user.isActivated}">
                              <span class="badge ms-2 badge-danger rounded-pill d-inline">
                                  <fmt:message key="user.blocked"/>
                              </span>
                            </c:if>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">
                                    <fmt:message key="account.full_name"/>
                                </p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${current_user.firstName} ${current_user.lastName}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">
                                    <fmt:message key="account.email"/>
                                </p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${current_user.email}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
