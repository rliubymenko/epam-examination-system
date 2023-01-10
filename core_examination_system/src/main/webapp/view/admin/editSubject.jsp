<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Edit Subject</title>
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
                <fmt:message key="subject.edit"/>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${pageContext.request.contextPath}/admins/subjects/subject?uuid=${subject.uuid}">

                    <div class="form-outline mb-4">
                        <input
                                type="text"
                                id="name"
                                name="name"
                                value="${subject.name}"
                                class="${not empty inconsistencies && (inconsistencies.contains('name') || inconsistencies.contains('used_name')) ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                required
                        />
                        <label class="form-label" for="name">
                            <fmt:message key="subject.name"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${empty inconsistencies || (not empty inconsistencies && inconsistencies.contains('name'))}">
                                <fmt:message key="subject.invalid_name"/>
                            </c:if>
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('used_name')}">
                                <fmt:message key="subject.invalid_used_name"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <textarea id="description"
                                  class="form-control form-control-lg"
                                  rows="15"
                                  name="description"
                        >${subject.description}</textarea>
                        <label class="form-label" for="description">
                            <fmt:message key="subject.description"/>
                        </label>
                    </div>

                    <button type="submit" class="btn btn-secondary btn-block"><fmt:message key="edit.edit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
