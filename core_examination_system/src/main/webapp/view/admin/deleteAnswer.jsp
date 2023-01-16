<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Change true answer</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<div class="row d-flex justify-content-center pt-4">
    <div class="col-4">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <fmt:message key="answer.change_true_answer"/>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${pageContext.request.contextPath}/admins/answers/answer/delete?uuid=${answer_uuid}">
                    <c:if test="${empty answers}">
                        <fmt:message key="answer.cannot_delete"/>
                        <a href="${pageContext.request.contextPath}/admins/answers"
                           type="button"
                           class="btn btn-secondary btn-block"
                        >
                            <fmt:message key="answer.go_to_answers"/>
                        </a>
                    </c:if>
                    <c:if test="${not empty answers}">
                        <h6 class="text-warning mb-4">
                            <fmt:message key="answer.deletion_single_choice"/>
                        </h6>
                        <div class="mb-4">
                            <small><fmt:message key="table.answers"/></small>
                            <select id="answerId" aria-label="Answer"
                                    name="new_true_answer_uuid"
                                    class="form-select">
                                <c:forEach var="toSelectAnswer" items="${answers}"
                                           varStatus="answerCounter">
                                    <option ${answerCounter.count == 1 ? 'selected' : ''}
                                            value="${toSelectAnswer.uuid}">
                                            ${toSelectAnswer.content}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-secondary btn-block"><fmt:message
                                key="table.save"/></button>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
