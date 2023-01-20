<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Edit Question</title>
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
                        <fmt:message key="question.edit"/>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <a href="${pageContext.request.contextPath}/admins/questions"
                           type="button"
                           class="btn btn-success">
                            <fmt:message key="edit.go_back_to_table"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${pageContext.request.contextPath}/admins/questions/question?uuid=${question.uuid}">

                    <div class="form-outline mb-4">
                        <textarea id="content"
                                  rows="10"
                                  name="content"
                                  class="${not empty inconsistencies && inconsistencies.contains('content') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                        >${question.content}</textarea>
                        <label class="form-label" for="content">
                            <fmt:message key="question.content"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('content')}">
                                <fmt:message key="question.invalid_content"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <textarea id="description"
                                  class="form-control form-control-lg"
                                  rows="5"
                                  name="description"
                        >${question.description}</textarea>
                        <label class="form-label" for="description">
                            <fmt:message key="question.description"/>
                        </label>
                    </div>

                    <button type="submit" class="btn btn-secondary btn-block">
                        <fmt:message key="edit.edit"/>
                    </button>

                </form>
            </div>
        </div>
    </div>
</div>

<div class="pt-4">
    <jsp:include page="/view/shared/footer.jsp"/>
</div>

</body>
</html>
