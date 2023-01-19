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
    <title>Subjects</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datatable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/subjectsForStudent.css">
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
              <tr data-mdb-toggle="collapse"
                  data-mdb-target="#collapse${counter.count}"
                  aria-expanded="false"
                  aria-controls="collapse${counter.count}">
                  <td>${counter.count}</td>
                  <td>${subject.name}</td>
                  <td style="width: 60rem">${subject.description}</td>
                  <td>
                      <a
                              id="${counter.count}Link"
                              role="button"
                              class="btn btn-link btn-rounded btn-sm fw-bold"
                              data-mdb-ripple-color="dark"
                              data-mdb-toggle="collapse"
                              href="#collapse${counter.count}"
                              aria-expanded="false"
                              aria-controls="collapse${counter.count}"
                      >
                          <fmt:message key="subject.view_tests"/>
                      </a>
                  </td>
              </tr>
              <tr id="collapse${counter.count}" class="collapse accordion-body testCollapse">
                  <td colspan="12">
                      <div class="row">
                          <c:if test="${empty subject.tests}">
                              <div class="text-center">
                                  <fmt:message key="table.nothing_to_show"/>
                              </div>
                          </c:if>
                          <table class="table table-hover">
                              <thead class="table-head">
                              <tr>
                                  <th><fmt:message key="#"/></th>
                                  <th><fmt:message key="test.name"/></th>
                                  <th><fmt:message key="test.complexity"/></th>
                                  <th><fmt:message key="test.expirationDate"/></th>
                                  <th><fmt:message key="test.maxAttemptNumber"/></th>
                              </tr>
                              </thead>
                              <tbody>
                              <c:forEach var="test" items="${subject.tests}" varStatus="testCounter">
                              <tr>
                                  <td>${testCounter.count}</td>
                                  <td>${test.name}</td>
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
                                  <td>
                                      <c:if test="${empty test.expirationDate}">
                                          <fmt:message key="test.unlimited"/>
                                      </c:if>
                                      <c:if test="${not empty test.expirationDate}">
                                          <eslib:datetime-formatter datetime="${test.expirationDate}"/>
                                      </c:if>
                                  </td>
                                  <td>${test.maxAttemptNumber}</td>
                              </tr>
                               </c:forEach>
                              </tbody>
                          </table>
                      </div>
                  </td>
              </tr>
          </c:forEach>
    </jsp:attribute>
</es:datatable>

</body>
</html>
