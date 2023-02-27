<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Edit User</title>
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
                                <fmt:message key="user.edit"/>
                            </div>
                            <div class="d-flex justify-content-end align-items-start">
                                <a href="${pageContext.request.contextPath}/passwordChange"
                                   type="button"
                                   class="btn btn-secondary me-2">
                                    <fmt:message key="password_change.password_change"/>
                                </a>
                                <a type="button"
                                   data-mdb-toggle="modal"
                                   data-mdb-target="#resetPassword"
                                   data-mdb-ripple-color="dark"
                                   class="btn btn-success me-2">
                                    <fmt:message key="edit.reset_password"/>
                                </a>
                                <a href="${pageContext.request.contextPath}/students/account"
                                   type="button"
                                   class="btn btn-success">
                                    <fmt:message key="edit.go_to_account"/>
                                </a>
                            </div>
                            <div
                                    class="modal fade"
                                    id="resetPassword"
                                    data-mdb-backdrop="static"
                                    data-mdb-keyboard="false"
                                    tabindex="-1"
                                    aria-labelledby="staticBackdropLabel"
                                    aria-hidden="true"
                            >
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h3 class="modal-title" id="staticBackdropLabel">
                                                <fmt:message key="edit.reset_password"/>
                                            </h3>
                                            <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="text-center">
                                                <h6 class="text-warning">
                                                    <fmt:message key="edit.reset_password_message"/>
                                                </h6>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <a href="${pageContext.request.contextPath}/students/account/reset/password"
                                               type="button"
                                               class="btn btn-secondary me-2">
                                                <fmt:message key="edit.send"/>
                                            </a>
                                            <button type="button" class="btn btn-secondary"
                                                    data-mdb-dismiss="modal">
                                                <fmt:message key="table.close"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form id="edit-user-form" method="post"
                              action="${pageContext.request.contextPath}/students/student?uuid=${user.uuid}">

                            <div class="input-group form-outline mb-5">
                                <span class="input-group-text" id="usernameGroup">@</span>
                                <input
                                        type="text"
                                        class="${not empty inconsistencies && (inconsistencies.contains('username') || inconsistencies.contains('used_username')) ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                        id="username"
                                        name="username"
                                        value="${user.username}"
                                        aria-label="<fmt:message key="user.username"/>"
                                        placeholder="<fmt:message key="user.username"/>"
                                        aria-describedby="usernameGroup"
                                />
                                <div class="invalid-feedback">
                                    <c:if test="${empty inconsistencies || (not empty inconsistencies && inconsistencies.contains('username'))}">
                                        <fmt:message key="user.invalid_username"/>
                                    </c:if>
                                    <c:if test="${not empty inconsistencies && inconsistencies.contains('used_username')}">
                                        <fmt:message key="user.invalid_used_username"/>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-outline mb-5">
                                <input
                                        type="email"
                                        id="email"
                                        name="email"
                                        value="${user.email}"
                                        class="${not empty inconsistencies && (inconsistencies.contains('email') || inconsistencies.contains('used_email')) ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                />
                                <label class="form-label" for="email">
                                    <fmt:message key="user.email"/>
                                </label>
                                <div class="invalid-feedback">
                                    <c:if test="${empty inconsistencies || (not empty inconsistencies && inconsistencies.contains('email'))}">
                                        <fmt:message key="user.invalid_email"/>
                                    </c:if>
                                    <c:if test="${not empty inconsistencies && inconsistencies.contains('used_email')}">
                                        <fmt:message key="user.invalid_used_email"/>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-outline mb-5">
                                <input
                                        type="text"
                                        id="firstname"
                                        name="firstname"
                                        value="${user.firstName}"
                                        class="${not empty inconsistencies && inconsistencies.contains('firstName') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                />
                                <label class="form-label" for="firstname">
                                    <fmt:message key="user.first_name"/>
                                </label>
                                <div class="invalid-feedback">
                                    <fmt:message key="user.invalid_firstname"/>
                                </div>
                            </div>

                            <div class="form-outline mb-5">
                                <input
                                        type="text"
                                        id="lastname"
                                        name="lastname"
                                        value="${user.lastName}"
                                        class="${not empty inconsistencies && inconsistencies.contains('lastName') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                />
                                <label class="form-label" for="lastname">
                                    <fmt:message key="user.last_name"/>
                                </label>
                                <div class="invalid-feedback">
                                    <fmt:message key="user.invalid_lastname"/>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-secondary btn-block">
                                <fmt:message key="edit.edit"/>
                            </button>
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
