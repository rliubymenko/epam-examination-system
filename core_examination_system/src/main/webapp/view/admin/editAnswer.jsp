<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Edit answer</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<c:set var="type" scope="page" value="${answer.question.type}"/>

<div class="row d-flex justify-content-center pt-4">
    <div class="col-6">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <fmt:message key="answer.edit"/>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <a href="${pageContext.request.contextPath}/admins/answers"
                           type="button"
                           class="btn btn-success">
                            <fmt:message key="edit.go_back_to_table"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${pageContext.request.contextPath}/admins/answers/answer?uuid=${answer.uuid}">
                    <c:if test="${empty answers}">
                        <div class="mb-4">
                            <small><fmt:message key="answer.question_type"/></small>
                            <h5>
                                <c:choose>
                                    <c:when test="${type == 'text'}">
                             <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.text"/>
                             </span>
                                    </c:when>
                                    <c:when test="${type == 'numerical'}">
                            <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.numerical"/>
                             </span>
                                    </c:when>
                                    <c:when test="${type == 'true_false'}">
                            <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.true_false"/>
                             </span>
                                    </c:when>
                                    <c:when test="${type == 'single_choice'}">
                            <span class="badge badge-info rounded-pill d-inline">
                                <fmt:message key="question.single_choice"/>
                             </span>
                                    </c:when>
                                    <c:when test="${type == 'multiple_choice'}">
                            <span class="badge badge-info rounded-pill d-inline">
                                <fmt:message key="question.multiple_choice"/>
                             </span>
                                    </c:when>
                                </c:choose>
                            </h5>
                            <hr>
                            <small><fmt:message key="answer.question"/></small>
                            <p>
                                    ${answer.question.content}
                            </p>
                            <hr>
                        </div>

                        <div class="form-outline mb-4">
                            <c:if test="${type == 'numerical'}">
                                <input
                                        type="number"
                                        id="numericalAnswer"
                                        name="content"
                                        value="${answer.content}"
                                        class="${not empty inconsistencies && inconsistencies.contains('content') ?
                                        'is-invalid form-control form-control-lg' :
                                        'form-control form-control-lg'}"
                                />
                                <label class="form-label" for="numericalAnswer">
                                    <fmt:message key="question.numerical"/>
                                </label>
                            </c:if>
                            <c:if test="${type != 'numerical'}">
                             <textarea id="content"
                                       rows="10"
                                       name="content"
                                       class="${not empty inconsistencies && inconsistencies.contains('content') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                             >${answer.content}</textarea>
                                <label class="form-label" for="content">
                                    <fmt:message key="answer.content"/>
                                </label>
                            </c:if>
                            <div class="invalid-feedback">
                                <c:if test="${not empty inconsistencies && inconsistencies.contains('content')}">
                                    <fmt:message key="answer.invalid_content"/>
                                </c:if>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${type == 'true_false' or type == 'multiple_choice' or type == 'single_choice'}">
                                <div class="form-outline mb-4">
                                    <label class="form-check-label" for="trueFalse">
                                        <fmt:message key="question.true_false"/>
                                    </label>
                                    <input
                                            id="trueFalse"
                                            class="${error_change_single_choice_true_answer ?
                                            'is-invalid form-check-input' :
                                            'form-check-input'}"
                                            type="checkbox"
                                            name="trueFalseAnswer"
                                        ${answer.isCorrect ? 'checked' : ''}
                                    />
                                    <div class="invalid-feedback">
                                        <c:if test="${error_change_single_choice_true_answer}">
                                            <fmt:message key="answer.error_change_single_choice_true_answer"/>
                                        </c:if>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:if>

                    <c:if test="${not empty answers}">

                        <h6 class="text-warning mb-4">
                            <fmt:message key="answer.change_single_choice_from_true_to_false"/>
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
                    </c:if>

                    <button type="submit" class="btn btn-secondary btn-block">
                        <fmt:message key="edit.edit"/>
                    </button>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
