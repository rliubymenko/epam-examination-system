<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<!-- Footer -->
<footer class="text-center text-white" style="background-color: #40C4FF">
    <!-- Grid container -->
    <div class="container">

        <!-- Section: Text -->
        <section class="pt-1">
            <div class="row d-flex justify-content-center">
                <div class="col-6">
                    <h6>
                        <fmt:message key="footer.info_title"/>
                    </h6>
                </div>
            </div>
            <div class="row d-flex justify-content-center">
                <div class="col-10">
                    <fmt:message key="footer.info_body"/>
                </div>
            </div>
        </section>
        <!-- Section: Text -->

    </div>
    <!-- Grid container -->

    <!-- Copyright -->
    <div class="footer-copyright">
        <ul>
            <li>
                <p>
                    © Copyright 2022 - 2023 :
                    <a class="text-white" href="${requestScope['jakarta.servlet.forward.context_path']}/home">
                        <fmt:message key="footer.сopyright"/>
                    </a>
                </p>
            </li>
            <li>
                <a
                        class="btn btn-primary btn-floating btn-sm mx-2 p-2"
                        style="background-color: #0082ca;"
                        href="https://www.linkedin.com/in/rliubymenko/"
                        role="button"
                >
                    <i class="fab fa-linkedin-in"></i>
                </a>
                <a
                        class="btn btn-primary btn-floating btn-sm mx-2 p-2"
                        style="background-color: #333333;"
                        href="https://github.com/rliubymenko"
                        role="button"
                >
                    <i class="fab fa-github"></i>
                </a>
                <span class="ps-2 align-self-center">
                    <fmt:message key="footer.developer_name"/>
                </span>
            </li>
        </ul>
    </div>
    <!-- Copyright -->
</footer>
<!-- Footer -->
