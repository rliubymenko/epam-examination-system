<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Registration page</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <jsp:include page="static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/registration.css">
    <script src="${pageContext.request.contextPath}/static/js/registration.js" defer></script>
    <c:if test="${empty sessionScope.locale or sessionScope.locale == 'en_US'}">
        <script src="https://www.google.com/recaptcha/api.js?hl=en" async defer></script>
    </c:if>
    <c:if test="${not empty sessionScope.locale and sessionScope.locale == 'uk_UA'}">
        <script src="https://www.google.com/recaptcha/api.js?hl=ua" async defer></script>
    </c:if>
    <script>
        $(document).ready(function () {
            const wrong_captcha = "${wrong_captcha}";
            const wrong_captcha_message = "<fmt:message key="login.wrong_captcha"/>";
            toastr.options = {
                "closeButton": true,
                "debug": false,
                "newestOnTop": false,
                "progressBar": true,
                "positionClass": "toast-top-right",
                "preventDuplicates": true,
                "onclick": null,
                "showDuration": "500",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            }
            if (wrong_captcha === 'true') {
                toastr["warning"](wrong_captcha_message)
            }
        });
    </script>
</head>
<body>

<div id="page-container">

    <jsp:include page="header.jsp"/>

    <div id="sign-up-wrap">
        <section class="vh-90 bg-image"
                 style="background-image: url('${pageContext.request.contextPath}/static/img/back.jpg');">
            <div class="mask d-flex align-items-center h-100 gradient-signup-form">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5"><fmt:message
                                            key="registration.creation"/></h2>
                                    <form
                                            id="registration-form"
                                            action="${pageContext.request.contextPath}/registration"
                                            method="post"
                                            novalidate
                                            class="needs-validation"
                                    >
                                        <div class="input-group form-outline mb-4">
                                            <span class="input-group-text" id="usernameGroup">@</span>
                                            <input
                                                    type="text"
                                                    class="${not empty inconsistencies && inconsistencies.contains('username') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                    id="username"
                                                    name="username"
                                                    aria-label="Username"
                                                    placeholder="<fmt:message key="registration.username"/>"
                                                    aria-describedby="usernameGroup"
                                                    required
                                            />
                                            <div class="invalid-feedback">
                                                <c:if test="${empty inconsistencies}">
                                                    <fmt:message key="registration.invalid_username"/>
                                                </c:if>
                                                <c:if test="${not empty inconsistencies && inconsistencies.contains('username')}">
                                                    <fmt:message key="registration.invalid_used_username"/>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input
                                                    type="email"
                                                    id="email"
                                                    name="email"
                                                    class="${not empty inconsistencies && inconsistencies.contains('email') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                    required
                                            />
                                            <label class="form-label" for="email">
                                                <fmt:message key="registration.email"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <c:if test="${empty inconsistencies}">
                                                    <fmt:message key="registration.invalid_email"/>
                                                </c:if>
                                                <c:if test="${not empty inconsistencies && inconsistencies.contains('email')}">
                                                    <fmt:message key="registration.invalid_used_email"/>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div id="password-div" class="form-outline mb-4">
                                            <input
                                                    type="password"
                                                    id="password"
                                                    name="password"
                                                    class="${not empty inconsistencies && inconsistencies.contains('password') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                    required
                                                    pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,50}"
                                            />
                                            <label class="form-label" for="password">
                                                <fmt:message key="registration.password"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <c:if test="${empty inconsistencies}">
                                                    <fmt:message key="registration.invalid_password"/>
                                                </c:if>
                                                <c:if test="${not empty inconsistencies && inconsistencies.contains('password')}">
                                                    <fmt:message key="registration.incorrect_password"/>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div id="repeated-password-div" class="form-outline mb-4">
                                            <input type="password"
                                                   id="repeated-password"
                                                   name="repeatedPassword"
                                                   class="${not empty inconsistencies && inconsistencies.contains('repeatedPassword') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                   required
                                                   pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{5,50}"
                                            />
                                            <label class="form-label" for="repeated-password">
                                                <fmt:message key="registration.repeat_password"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <c:if test="${empty inconsistencies}">
                                                    <fmt:message key="registration.invalid_password"/>
                                                </c:if>
                                                <c:if test="${not empty inconsistencies && inconsistencies.contains('repeatedPassword')}">
                                                    <fmt:message key="registration.invalid_passwords_match"/>
                                                </c:if>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input
                                                    type="text"
                                                    id="firstname"
                                                    name="firstname"
                                                    class="${not empty inconsistencies && inconsistencies.contains('firstName') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                    required
                                                    pattern="([А-Яа-яЁёЇїІіЄєҐґ'A-Za-z]){1,30}"
                                            />
                                            <label class="form-label" for="firstname">
                                                <fmt:message key="registration.firstname"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <fmt:message key="registration.invalid_firstname"/>
                                            </div>
                                        </div>
                                        <div class="form-outline mb-4">
                                            <input
                                                    type="text"
                                                    id="lastname"
                                                    name="lastname"
                                                    class="${not empty inconsistencies && inconsistencies.contains('lastName') ?
                                            'is-invalid form-control form-control-lg' :
                                            'form-control form-control-lg'}"
                                                    required
                                                    pattern="([А-Яа-яЁёЇїІіЄєҐґ'A-Za-z]){1,50}"
                                            />
                                            <label class="form-label" for="lastname">
                                                <fmt:message key="registration.lastname"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <fmt:message key="registration.invalid_lastname"/>
                                            </div>
                                        </div>
                                        <div class="mb-4">
                                            <div class="g-recaptcha"
                                                 data-sitekey="6LfdqjYkAAAAAJGiHTSJjEfmLxaD-p1DbCxeC8wt"></div>
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-submit-btn text-body">
                                                <fmt:message key="registration.signup"/>
                                            </button>
                                        </div>
                                        <p class="text-center text-muted mt-5 mb-0">
                                            <fmt:message key="registration.already_have_account"/>
                                            <a href="${pageContext.request.contextPath}/login"
                                               class="fw-bold text-body">
                                                <u>
                                                    <fmt:message key="registration.login_here"/>
                                                </u>
                                            </a>
                                        </p>
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
