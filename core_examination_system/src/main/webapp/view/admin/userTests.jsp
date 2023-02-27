<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="eslib" uri="customtaglib" %>


<html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<head>
    <title>User Tests</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datatable.css">
    <script src="${pageContext.request.contextPath}/static/js/datatable.js" defer></script>
</head>
<body>

<div id="page-container">

    <jsp:include page="/view/shared/header.jsp"/>

    <div id="content-wrap">
        <es:datatable
                headerDataList="${headerDataList}"
                allowCreate="${allowCreate}"
                pageData="${pageData}"
                cardHeader="${cardHeader}"
                createNewItemUrl="${createNewItemUrl}"
                reportPath="${reportPath}"
                searchHeaderNames="${['table.users', 'table.tests']}">
            <jsp:attribute name="body">
                  <c:forEach var="userTest" items="${pageData.items}" varStatus="counter">
                      <tr>
                          <td>${counter.count}</td>
                          <td>${userTest.user.username}</td>
                          <td>${userTest.test.name}</td>
                          <td>
                              <c:if test="${empty userTest.startTime}">
                                    <fmt:message key="user_test.not_started"/>
                              </c:if>
                              <c:if test="${not empty userTest.startTime}">
                                  <eslib:datetime-formatter datetime="${userTest.startTime}"/>
                              </c:if>
                          </td>
                          <td>
                              <c:if test="${empty userTest.endTime}">
                                    <fmt:message key="user_test.not_started"/>
                              </c:if>
                              <c:if test="${not empty userTest.endTime}">
                                  <eslib:datetime-formatter datetime="${userTest.endTime}"/>
                              </c:if>
                          </td>
                          <td>${userTest.isSelected}</td>
                          <td>
                               <c:if test="${not userTest.isCompleted}">
                                    <fmt:message key="user_test.not_completed"/>
                              </c:if>
                              <c:if test="${userTest.isCompleted}">
                                  ${userTest.isCompleted}
                              </c:if>
                          </td>
                          <td>
                              <c:if test="${empty userTest.markValue}">
                                    <fmt:message key="user_test.not_graded_work"/>
                              </c:if>
                              <c:if test="${not empty userTest.markValue}">
                                  ${userTest.markValue}
                              </c:if>
                          </td>
                          <td>${userTest.attemptNumber}</td>
                          <td>
                              <a href="${pageContext.request.contextPath}/admins/users/user?uuid=${userTest.user.uuid}"
                                 type="button"
                                 class="btn btn-link btn-rounded btn-sm fw-bold"
                                 data-mdb-ripple-color="dark">
                                  <fmt:message key="user_test.go_to_user"/>
                              </a>
                              <a href="${pageContext.request.contextPath}/admins/tests/test?uuid=${userTest.test.uuid}"
                                 type="button"
                                 class="btn btn-link btn-rounded btn-sm fw-bold"
                                 data-mdb-ripple-color="dark">
                                  <fmt:message key="user_test.go_to_test"/>
                              </a>
                          </td>
                      </tr>
                  </c:forEach>
            </jsp:attribute>
        </es:datatable>
    </div>

    <jsp:include page="/view/shared/footer.jsp"/>

</div>

</body>
</html>
