<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datatable.css">
    <script src="${pageContext.request.contextPath}/static/js/datatable.js" defer></script>
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<es:datatable
        headerDataList="${headerDataList}"
        allowCreate="${allowCreate}"
        pageData="${pageData}"
        cardHeader="${cardHeader}"
        createNewItemUrl="${createNewItemUrl}">
    <jsp:attribute name="body">
          <c:forEach var="user" items="${pageData.items}" varStatus="counter">
              <tr>
                  <td>${counter.count}</td>
                  <td>${user.username}</td>
                  <td>${user.email}</td>
                  <td>${user.firstName}</td>
                  <td>${user.lastName}</td>
                  <td>
                      <c:if test="${user.isActivated}">
                          <span class="badge badge-success rounded-pill d-inline">
                              <fmt:message key="user.active"/>
                          </span>
                      </c:if>
                      <c:if test="${!user.isActivated}">
                          <span class="badge badge-danger rounded-pill d-inline">
                              <fmt:message key="user.blocked"/>
                          </span>
                      </c:if>
                  </td>
                  <td>
                      <c:if test="${user.role == 'student' }">
                          <fmt:message key="role.student"/>
                      </c:if>
                      <c:if test="${user.role == 'admin'}">
                          <fmt:message key="role.admin"/>
                      </c:if>
                  </td>
                  <td>
                      <a href="${pageContext.request.contextPath}/admins/users/user?uuid=${user.uuid}"
                         type="button"
                         class="btn btn-link btn-rounded btn-sm fw-bold"
                         data-mdb-ripple-color="dark">
                          <fmt:message key="table.edit"/>
                      </a>
                  </td>
              </tr>
          </c:forEach>
    </jsp:attribute>
</es:datatable>

<div class="pt-4">
    <jsp:include page="/view/shared/footer.jsp"/>
</div>

</body>
</html>
