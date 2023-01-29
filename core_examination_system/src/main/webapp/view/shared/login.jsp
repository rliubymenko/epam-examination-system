<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/js/login.js" defer></script>
    <c:if test="${empty sessionScope.locale or sessionScope.locale == 'en_US'}">
        <script src="https://www.google.com/recaptcha/api.js?hl=en" async defer></script>
    </c:if>
    <c:if test="${not empty sessionScope.locale and sessionScope.locale == 'uk_UA'}">
        <script src="https://www.google.com/recaptcha/api.js?hl=ua" async defer></script>
    </c:if>
    <script>
        $(document).ready(function () {
            const wrong_username = "${wrong_username}";
            const wrong_password = "${wrong_password}";
            const wrong_captcha = "${wrong_captcha}";
            const wrong_username_message = "<fmt:message key="login.wrong_username"/>";
            const wrong_password_message = "<fmt:message key="login.wrong_password"/>";
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
            if (wrong_username === 'true') {
                toastr["warning"](wrong_username_message)
            }
            if (wrong_password === 'true') {
                toastr["warning"](wrong_password_message)
            }
        });
    </script>
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
                                    <h2 class="text-uppercase text-center mb-5"><fmt:message key="login.login"/></h2>
                                    <form
                                            id="login-form"
                                            action="${pageContext.request.contextPath}/login"
                                            method="post"
                                            novalidate
                                            class="needs-validation"
                                    >
                                        <div class="input-group form-outline mb-4">
                                            <span class="input-group-text" id="usernameGroup">@</span>
                                            <input
                                                    type="text"
                                                    class="form-control form-control-lg"
                                                    id="username"
                                                    name="username"
                                                    aria-label="Username"
                                                    placeholder="<fmt:message key="login.username"/>"
                                                    aria-describedby="usernameGroup"
                                                    required
                                            />
                                            <div class="invalid-feedback">
                                                <fmt:message key="login.invalid_username"/>
                                            </div>
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input
                                                    type="password"
                                                    id="password"
                                                    name="password"
                                                    class="form-control form-control-lg"
                                                    required
                                            />
                                            <label class="form-label" for="password">
                                                <fmt:message key="login.password"/>
                                            </label>
                                            <div class="invalid-feedback">
                                                <fmt:message key="login.invalid_password"/>
                                            </div>
                                        </div>
                                        <div class="mb-4">
                                            <div class="g-recaptcha"
                                                 data-sitekey="6LfdqjYkAAAAAJGiHTSJjEfmLxaD-p1DbCxeC8wt"></div>
                                        </div>
                                        <div class="d-flex justify-content-center">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-submit-btn text-body">
                                                <fmt:message key="login.signin"/>
                                            </button>
                                        </div>
                                        <p class="text-center text-muted mt-5 mb-0">
                                            <fmt:message key="login.do_not_have_account"/>
                                            <a href="${pageContext.request.contextPath}/registration"
                                               class="fw-bold text-body">
                                                <u>
                                                    <fmt:message key="login.register_here"/>
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
