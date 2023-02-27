<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>New Subject</title>
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
        <div class="row d-flex justify-content-center">
            <div class="col-6">
                <div class="card bg-light border border-primary shadow-0">
                    <div class="card-header">
                        <div class="d-flex justify-content-between">
                            <div class="d-flex justify-content-start align-items-start">
                                <fmt:message key="subject.new"/>
                            </div>
                            <div class="d-flex justify-content-end align-items-start">
                                <a href="${pageContext.request.contextPath}/admins/subjects"
                                   type="button"
                                   class="btn btn-success">
                                    <fmt:message key="edit.go_back_to_table"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form method="post"
                              action="${pageContext.request.contextPath}/admins/subjects/subject/new">

                            <div class="form-outline mb-4">
                                <input
                                        type="text"
                                        id="name"
                                        name="name"
                                        class="${not empty inconsistencies && (inconsistencies.contains('name') || inconsistencies.contains('used_name')) ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
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
                        ></textarea>
                                <label class="form-label" for="description">
                                    <fmt:message key="subject.description"/>
                                </label>
                            </div>

                            <button type="submit" class="btn btn-secondary btn-block"><fmt:message
                                    key="edit.new"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/view/shared/footer.jsp"/>

</div>

</body>
</html>
