<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Testing</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/testing.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/lib/bs-stepper.min.css">
    <script src="${pageContext.request.contextPath}/static/js/lib/bs-stepper.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/lib/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/lib/jquery.countdown.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/testing.js"></script>
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<div class="container">
    <div class="row pt-4">
        <div class="row rounded-pill d-flex justify-content-center">
            <div class="col-4">
                <div class="card rounded-pill countdown-gradient bg-light shadow-0">
                    <div class="card-body text-white text-center">
                        <h5 class="fw-bold text-uppercase pe-2">
                            <fmt:message key="test.time_left"/>
                        </h5>
                        <div id="clock" data-countdown="${test.duration}" class="countdown"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row pt-4">
            <div class="col-12">
                <div class="card bg-light shadow-0">
                    <div class="card-header">
                        <h3>${test.name}</h3>
                    </div>
                    <div class="card-body pt-0">
                        <div id="questionStepper" class="bs-stepper">
                            <div class="bs-stepper-header" role="tablist">
                                <c:if test="${not empty test.questions}">
                                    <c:forEach var="question" items="${test.questions}" varStatus="questionStatus">
                                        <c:if test="${not empty question.answers}">
                                            <div class="step" data-target="#toggle${questionStatus.index}">
                                                <button
                                                        id="stepperTrigger${questionStatus.index}"
                                                        type="button"
                                                        role="tab"
                                                        class="step-trigger"
                                                        aria-controls="toggle${questionStatus.index}"
                                                >
                                                    <span class="bs-stepper-circle">${questionStatus.count}</span>
                                                </button>
                                            </div>
                                            <c:if test="${not questionStatus.last}">
                                                <div class="bs-stepper-line"></div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty question.answers}">
                                            <div class="col-1">
                                                <h5 class="text-center">
                                                    <fmt:message key="test.in_development_process"/>
                                                </h5>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty test.questions}">
                                    <h5 class="text-center">
                                        <fmt:message key="test.in_development_process"/>
                                    </h5>
                                </c:if>
                            </div>

                            <div class="bs-stepper-content">
                                <form id="testForm" method="post"
                                      action="${pageContext.request.contextPath}/students/tests/testing?uuid=${test.uuid}">
                                    <c:if test="${fn:length(test.questions) == 1}">
                                        <div id="toggle0"
                                             role="tabpanel"
                                             class="bs-stepper-pane text-center"
                                             aria-labelledby="stepperTrigger0">

                                            <es:questionStep question="${test.questions[0]}"
                                                             questionId="0"/>

                                            <button id="completeBtn" type="button"
                                                    class="btn btn-success">
                                                <fmt:message key="test.complete"/>
                                            </button>
                                        </div>
                                    </c:if>
                                    <c:if test="${fn:length(test.questions) != 1}">
                                        <c:forEach var="question" items="${test.questions}" varStatus="questionStatus">
                                            <c:if test="${not empty question.answers}">
                                                <c:choose>
                                                    <c:when test="${questionStatus.first}">
                                                        <div id="toggle0" role="tabpanel" class="bs-stepper-pane"
                                                             aria-labelledby="stepperTrigger0">

                                                            <es:questionStep question="${question}"
                                                                             questionId="${questionStatus.index}"/>

                                                            <button type="button" class="btn btn-primary"
                                                                    onclick="stepNext()">
                                                                <fmt:message key="question.next"/>
                                                            </button>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${questionStatus.last}">
                                                        <div id="toggle${questionStatus.index}"
                                                             role="tabpanel"
                                                             class="bs-stepper-pane text-center"
                                                             aria-labelledby="stepperTrigger${questionStatus.index}">

                                                            <es:questionStep question="${question}"
                                                                             questionId="${questionStatus.index}"/>

                                                            <button type="button" class="btn btn-primary"
                                                                    onclick="stepBack()">
                                                                <fmt:message key="question.previous"/>
                                                            </button>
                                                            <button id="completeBtn" type="button"
                                                                    class="btn btn-success">
                                                                <fmt:message key="test.complete"/>
                                                            </button>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div id="toggle${questionStatus.index}"
                                                             role="tabpanel"
                                                             class="bs-stepper-pane"
                                                             aria-labelledby="stepperTrigger${questionStatus.index}">

                                                            <es:questionStep question="${question}"
                                                                             questionId="${questionStatus.index}"/>

                                                            <button type="button" class="btn btn-primary"
                                                                    onclick="stepBack()">
                                                                <fmt:message key="question.previous"/>
                                                            </button>
                                                            <button type="button" class="btn btn-primary"
                                                                    onclick="stepNext()">
                                                                <fmt:message key="question.next"/>
                                                            </button>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
