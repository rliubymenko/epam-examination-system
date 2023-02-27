<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Password Change</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/js/passwordChange.js" defer></script>
</head>
<body>

<div id="page-container">

    <jsp:include page="header.jsp"/>

    <div id="login-content-wrap">
        <section class="vh-90 bg-image"
                 style="background-image: url('${pageContext.request.contextPath}/static/img/back.jpg');">
            <div class="mask d-flex align-items-center h-100 gradient-signin-form">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center login-height">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5"><fmt:message
                                            key="password_change.password_change"/></h2>
                                    <form
                                            id="login-form"
                                            action="${pageContext.request.contextPath}/passwordChange"
                                            method="post"
                                            novalidate
                                            class="needs-validation"
                                    >
                                        <div id="oldPasswordDiv" class="form-outline input-group mb-5">
                                             <span class="input-group-text border-0" id="oldPasswordVisibility">
                                                <i class="fas fa-eye-slash" id="toggleOldPassword"></i>
                                             </span>
                                            <input
                                                    type="password"
                                                    id="oldPassword"
                                                    name="oldPassword"
                                                    class="${not empty inconsistencies && inconsistencies.contains('oldPassword') ?
                                                        'is-invalid rounded form-control form-control-lg' :
                                                        'form-control rounded form-control-lg'}"
                                                    placeholder="<fmt:message key="password_change.old_password"/>"
                                                    aria-label="<fmt:message key="password_change.old_password"/>"
                                                    aria-describedby="passwordVisibility"
                                            />
                                            <div class="invalid-feedback">
                                                <fmt:message key="password_change.invalid_old_password"/>
                                            </div>
                                        </div>

                                        <div id="newPasswordDiv" class="form-outline input-group mb-5">
                                             <span class="input-group-text border-0" id="newPasswordVisibility">
                                                <i class="fas fa-eye-slash" id="toggleNewPassword"></i>
                                             </span>
                                            <input
                                                    type="password"
                                                    id="newPassword"
                                                    name="newPassword"
                                                    class="${not empty inconsistencies && inconsistencies.contains('newPassword') ?
                                            'is-invalid rounded form-control form-control-lg' :
                                            'form-control rounded form-control-lg'}"
                                                    placeholder="<fmt:message key="password_change.new_password"/>"
                                                    aria-label="<fmt:message key="password_change.new_password"/>"
                                                    aria-describedby="passwordVisibility"
                                            />
                                            <div class="invalid-feedback">
                                                <fmt:message key="registration.invalid_password"/>
                                            </div>
                                        </div>

                                        <div id="repeatedPasswordDiv" class="form-outline input-group mb-5">
                                             <span class="input-group-text border-0" id="repeatedPasswordVisibility">
                                                <i class="fas fa-eye-slash" id="toggleRepeatedPassword"></i>
                                             </span>
                                            <input type="password"
                                                   id="repeatedPassword"
                                                   name="repeatedPassword"
                                                   class="${not empty inconsistencies && inconsistencies.contains('repeatedPassword') ?
                                            'is-invalid rounded form-control form-control-lg' :
                                            'form-control rounded form-control-lg'}"
                                                   placeholder="<fmt:message key="password_change.repeat_new_password"/>"
                                                   aria-label="<fmt:message key="password_change.repeat_new_password"/>"
                                                   aria-describedby="passwordVisibility"
                                            />
                                            <div class="invalid-feedback">
                                                <fmt:message key="registration.invalid_passwords_match"/>
                                            </div>
                                        </div>

                                        <div class="d-flex justify-content-center">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-submit-btn text-body">
                                                <fmt:message key="password_change.submit"/>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="footer.jsp"/>

</div>

</body>
</html>
