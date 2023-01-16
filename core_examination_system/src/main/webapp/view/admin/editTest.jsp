<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<head>
    <title>Edit test</title>
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
    <div class="col-6">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <fmt:message key="test.edit"/>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <a href="${pageContext.request.contextPath}/admins/tests"
                           type="button"
                           class="btn btn-success">
                            <fmt:message key="edit.go_back_to_table"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form id="edit-user-form" method="post"
                      action="${pageContext.request.contextPath}/admins/tests/test?uuid=${test.uuid}">

                    <div class="form-outline mb-4">
                        <input
                                type="text"
                                id="name"
                                name="name"
                                value="${test.name}"
                                class="${not empty inconsistencies && inconsistencies.contains('name') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                        />
                        <label class="form-label" for="name">
                            <fmt:message key="test.name"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('name')}">
                                <fmt:message key="test.invalid_name"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <textarea id="description"
                                  class="form-control form-control-lg"
                                  rows="5"
                                  name="description"
                        >${test.description}</textarea>
                        <label class="form-label" for="description">
                            <fmt:message key="test.description"/>
                        </label>
                    </div>

                    <div class="form-outline mb-4">
                        <input
                                type="number"
                                id="duration"
                                name="duration"
                                value="${test.duration}"
                                class="${not empty inconsistencies && inconsistencies.contains('duration') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                        />
                        <label class="form-label" for="duration">
                            <fmt:message key="test.duration"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('duration')}">
                                <fmt:message key="test.invalid_duration"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <input
                                type="number"
                                id="maxAttemptNumber"
                                name="maxAttemptNumber"
                                value="${test.maxAttemptNumber}"
                                class="${not empty inconsistencies && inconsistencies.contains('maxAttemptNumber') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                        />
                        <label class="form-label" for="maxAttemptNumber">
                            <fmt:message key="test.maxAttemptNumber"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('maxAttemptNumber')}">
                                <fmt:message key="test.invalid_maxAttemptNumber"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <input
                                type="datetime-local"
                                id="expirationDate"
                                name="expirationDate"
                                value="${test.expirationDate}"
                                class="form-control form-control-lg"
                        />
                        <label class="form-label" for="expirationDate">
                            <fmt:message key="test.expirationDate"/>
                        </label>
                    </div>

                    <div class="mb-4">
                        <small><fmt:message key="test.complexity"/></small>
                        <select name="complexity" class="form-select" aria-label="<fmt:message key="test.complexity"/>">
                            <option value="easy" ${test.complexity == 'easy' ? 'selected' : ''}>
                                <fmt:message key="test.easy"/>
                            </option>
                            <option value="moderate" ${test.complexity == 'moderate' ? 'selected' : ''}>
                                <fmt:message key="test.moderate"/>
                            </option>
                            <option value="hard" ${test.complexity == 'hard' ? 'selected' : ''}>
                                <fmt:message key="test.hard"/>
                            </option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-secondary btn-block"><fmt:message key="edit.edit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
