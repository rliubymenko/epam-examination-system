<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Main page</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="static.jsp"/>
    <script src="${pageContext.request.contextPath}/static/js/home.js"></script>
</head>
<body>

<div id="page-container">
    <jsp:include page="header.jsp"/>
    <div id="content-wrap">
        <div class="row">
            <div class="homeMessage col-6">
                <p id="welcome">
                    <fmt:message key="home.welcome_examination_system"/>
                </p>
            </div>
            <div class="col-6">
                <c:if test="${not empty subjects}">
                    <div class="row subjectList ps-5 pe-5 d-flex justify-content-center">
                        <div class="row">
                            <div class="card bg-light mb-3">
                                <div class="card-body">
                                    <div class="text-center">
                                        <h4>
                                            <fmt:message key="home.welcome_with_list_of_subjects"/>
                                        </h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:forEach var="subject" items="${subjects}" varStatus="subjectStatus">
                            <div class="row">
                                <div class="card bg-light mb-3">
                                    <div class="card-header">
                                        <div class="text-center">
                                                ${subject.name}
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="text-center">
                                                ${subject.description}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>

</body>
</html>
