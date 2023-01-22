<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="eslib" uri="customtaglib" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Student account</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
</head>
<body>

<div id="page-container">

    <jsp:include page="/view/shared/header.jsp"/>

    <div id="content-wrap">
        <section>
            <div class="container">

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
                                    <c:if test="${current_user.role == 'student' }">
                                        <fmt:message key="role.student"/>
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

                        <c:if test="${empty userTests}">
                            <div class="card">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-12 text-center">
                                            <h5>
                                                <fmt:message key="user_test.not_found"/>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${not empty userTests}">
                            <div class="row">
                                <c:forEach var="userTest" items="${userTests}" varStatus="userTestStatus">
                                    <div class="col-6">
                                        <div class="card mb-4">
                                            <div class="card-body">
                                                <p class="mb-4">
                                            <span class="text-primary font-italic">
                                                <strong>${userTest.test.name}</strong>
                                            </span>
                                                </p>
                                                <p class="mb-1">
                                                    <i class="fas fa-star fa-lg text-warning"></i>
                                                    <span class="mx-2">|</span>
                                                    <c:choose>
                                                        <c:when test="${userTest.test.complexity == 'hard'}">
                                                     <span class="badge badge-danger rounded-pill d-inline">
                                                        <fmt:message key="test.hard"/> <fmt:message
                                                             key="test.difficulty_level"/>
                                                     </span>
                                                        </c:when>
                                                        <c:when test="${userTest.test.complexity == 'moderate'}">
                                                    <span class="badge badge-warning rounded-pill d-inline">
                                                        <fmt:message key="test.moderate"/> <fmt:message
                                                            key="test.difficulty_level"/>
                                                     </span>
                                                        </c:when>
                                                        <c:otherwise>
                                                     <span class="badge badge-success rounded-pill d-inline">
                                                         <fmt:message key="test.easy"/> <fmt:message
                                                             key="test.difficulty_level"/>
                                                    </span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </p>
                                                <hr>
                                                <p class="mb-1">
                                                    <span><fmt:message key="test.expirationDate"/>: </span>
                                                    <c:if test="${empty userTest.test.expirationDate}">
                                                        <fmt:message key="test.unlimited"/>
                                                    </c:if>
                                                    <c:if test="${not empty userTest.test.expirationDate}">
                                                        <eslib:datetime-formatter
                                                                datetime="${userTest.test.expirationDate}"/>
                                                    </c:if>
                                                </p>
                                                <hr>
                                                <p class="mb-1">
                                             <span>${userTest.test.duration} <fmt:message
                                                     key="test.minutes_to_take"/></span>
                                                </p>
                                                <hr>
                                                <p class="mb-1">
                                                    <span><fmt:message key="test.maxAttemptNumber"/>: </span>
                                                    <span>${userTest.test.maxAttemptNumber}</span>
                                                </p>
                                                <hr>
                                                <p class="mb-1">
                                                    <span><fmt:message key="user_test.attempt_number"/>: </span>
                                                    <span>${userTest.attemptNumber}</span>
                                                </p>
                                                <hr>
                                                <c:if test="${userTest.isCompleted}">
                                                    <p class="mb-1">
                                                        <span><fmt:message key="user_test.start_time"/>: </span>
                                                        <span>
                                                    <eslib:datetime-formatter datetime="${userTest.startTime}"/>
                                                </span>
                                                    </p>
                                                    <p class="mb-1">
                                                        <span><fmt:message key="user_test.end_time"/>: </span>
                                                        <span>
                                                    <eslib:datetime-formatter datetime="${userTest.endTime}"/>
                                                </span>
                                                    </p>
                                                    <hr>
                                                    <small class="mt-4 mb-1">
                                                            ${userTest.markValue} % <fmt:message
                                                            key="user_test.correct"/>
                                                    </small>
                                                    <div class="progress rounded" style="height: 20px;">
                                                        <div class="progress-bar progress-bar-striped"
                                                             role="progressbar"
                                                             style="width: ${userTest.markValue}%"
                                                             aria-valuenow="${userTest.markValue}"
                                                             aria-valuemin="0" aria-valuemax="100">
                                                                ${userTest.markValue}%
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <hr>
                                                <c:if test="${userTest.test.maxAttemptNumber >= userTest.attemptNumber + 1}">
                                                    <div class="text-center">
                                                        <button type="button"
                                                                class="btn btn-outline-success"
                                                                data-mdb-toggle="modal"
                                                                data-mdb-target="#testConfirmationModal${userTest.test.uuid}"
                                                                data-mdb-ripple-color="dark"
                                                        >
                                                            <fmt:message key="test.start_testing"/>
                                                        </button>
                                                    </div>
                                                    <es:confirmationTestStartModal
                                                            modalId="${userTest.test.uuid}"
                                                            testingUrl="${pageContext.request.contextPath}/students/tests/testing?uuid=${userTest.test.uuid}"
                                                            testDuration="${userTest.test.duration}"
                                                            testMaxAttemptNumber="${userTest.test.maxAttemptNumber}"
                                                            testCurrentAttemptNumber="${userTest.attemptNumber}"/>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="/view/shared/footer.jsp"/>

</div>

</body>
</html>
