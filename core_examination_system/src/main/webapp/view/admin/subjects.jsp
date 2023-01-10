<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Subjects</title>
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
          <c:forEach var="subject" items="${pageData.items}" varStatus="counter">
              <tr>
                  <td>${counter.count}</td>
                  <td>${subject.name}</td>
                  <td style="width: 60rem">${subject.description}</td>
                  <td>
                      <a href="${pageContext.request.contextPath}/admins/subjects/subject?uuid=${subject.uuid}"
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

</body>
</html>
