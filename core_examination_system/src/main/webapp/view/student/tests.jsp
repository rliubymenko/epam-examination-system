<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="eslib" uri="customtaglib" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Tests</title>
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
          <c:forEach var="test" items="${pageData.items}" varStatus="counter">
              <tr>
                  <td>${counter.count}</td>
                  <td>${test.name}</td>
                  <td>${test.description}</td>
                  <td>
                      <c:choose>
                         <c:when test="${test.complexity == 'hard'}">
                             <span class="badge badge-danger rounded-pill d-inline">
                                <fmt:message key="test.hard"/>
                             </span>
                         </c:when>
                         <c:when test="${test.complexity == 'moderate'}">
                            <span class="badge badge-warning rounded-pill d-inline">
                                <fmt:message key="test.moderate"/>
                             </span>
                         </c:when>
                         <c:otherwise>
                             <span class="badge badge-success rounded-pill d-inline">
                                 <fmt:message key="test.easy"/>
                            </span>
                         </c:otherwise>
                      </c:choose>
                  </td>
                  <td>${test.duration}</td>
                  <td>
                      <eslib:datetime-formatter datetime="${test.creationDate}"/>
                  </td>
                  <td>
                      <c:if test="${empty test.expirationDate}">
                            <fmt:message key="test.unlimited"/>
                      </c:if>
                      <c:if test="${not empty test.expirationDate}">
                          <eslib:datetime-formatter datetime="${test.expirationDate}"/>
                      </c:if>
                  </td>
                  <td>${test.maxAttemptNumber}</td>
                  <td>${test.totalAttemptNumber}</td>
                  <td>
                      <c:if test="${not empty test.subject}">
                            ${test.subject.name}
                      </c:if>
                      <c:if test="${empty test.subject}">
                             <fmt:message key="test.empty"/>
                      </c:if>
                  </td>
                  <td>
                      <div class="d-flex flex-column">
                          <button type="button"
                                  class="btn text-success btn-link btn-rounded btn-sm fw-bold"
                                  data-mdb-toggle="modal"
                                  data-mdb-target="#testConfirmationModal${counter.count}"
                                  data-mdb-ripple-color="dark"
                          >
                              <fmt:message key="test.start_testing"/>
                          </button>
                          <c:if test="${not test.isSelected}">
                               <a href="${pageContext.request.contextPath}/students/tests/test/select?uuid=${test.uuid}"
                                  type="button"
                                  class="btn btn-link text-warning btn-rounded btn-sm fw-bold"
                                  data-mdb-ripple-color="dark">
                                   <fmt:message key="test.select"/>
                               </a>
                          </c:if>
                      </div>
                      <es:confirmationTestStartModal
                              modalId="${counter.count}"
                              testingUrl="${pageContext.request.contextPath}/students/tests/testing?uuid=${test.uuid}"
                              testDuration="${test.duration}"
                              testMaxAttemptNumber="${test.maxAttemptNumber}"
                              testCurrentAttemptNumber="${test.currentAttemptNumber}"/>
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
