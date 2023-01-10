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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<div class="row d-flex justify-content-center pt-4">
    <div class="col-6">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <fmt:message key="user.edit"/>
            </div>
            <div class="card-body">
                <form id="edit-user-form" method="post"
                      action="${pageContext.request.contextPath}/admins/users/user?uuid=${user.uuid}">

                    <div class="input-group form-outline mb-4">
                        <span class="input-group-text" id="usernameGroup">@</span>
                        <input
                                type="text"
                                class="${not empty inconsistencies && inconsistencies.contains('username') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                id="username"
                                name="username"
                                value="${user.username}"
                                aria-label="<fmt:message key="user.username"/>"
                                placeholder="<fmt:message key="user.username"/>"
                                aria-describedby="usernameGroup"
                                required
                        />
                        <div class="invalid-feedback">
                            <fmt:message key="user.invalid_username"/>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <input
                                type="email"
                                id="email"
                                name="email"
                                value="${user.email}"
                                class="${not empty inconsistencies && inconsistencies.contains('email') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                required
                        />
                        <label class="form-label" for="email">
                            <fmt:message key="user.email"/>
                        </label>
                        <div class="invalid-feedback">
                            <fmt:message key="user.invalid_email"/>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <input
                                type="password"
                                id="password"
                                name="password"
                                value="${user.password}"
                                class="form-control form-control-lg"
                                required
                        />
                        <label class="form-label" for="password">
                            <fmt:message key="user.password"/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <input
                                type="text"
                                id="firstname"
                                name="firstname"
                                value="${user.firstName}"
                                class="${not empty inconsistencies && inconsistencies.contains('firstName') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                required
                        />
                        <label class="form-label" for="firstname">
                            <fmt:message key="user.first_name"/>
                        </label>
                        <div class="invalid-feedback">
                            <fmt:message key="user.invalid_firstname"/>
                        </div>
                    </div>
                    <div class="form-outline mb-4">
                        <input
                                type="text"
                                id="lastname"
                                name="lastname"
                                value="${user.lastName}"
                                class="${not empty inconsistencies && inconsistencies.contains('lastName') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                                required
                        />
                        <label class="form-label" for="lastname">
                            <fmt:message key="user.last_name"/>
                        </label>
                        <div class="invalid-feedback">
                            <fmt:message key="user.invalid_lastname"/>
                        </div>
                    </div>
                    <div class="form-check d-flex justify-content-center mb-4">
                        <input
                                id="is_activated"
                                class="form-check-input me-2"
                                type="checkbox"
                                name="isActivated"
                        ${user.isActivated ? 'checked' : ''}
                        />
                        <label class="form-check-label" for="is_activated">
                            <fmt:message key="user.is_activated"/>
                        </label>
                    </div>
                    <button type="submit" class="btn btn-secondary btn-block"><fmt:message key="table.edit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
