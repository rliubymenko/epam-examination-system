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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/datatable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/subjectsForStudent.css">
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
                                          <h6><fmt:message key="table.nothing_to_show"/></h6>
                                      </div>
                                  </c:if>
                                  <c:if test="${not empty subject.tests}">
                                        <table class="table table-hover">
                                            <thead class="table-head">
                                            <tr>
                                                <th><fmt:message key="#"/></th>
                                                <th><fmt:message key="test.name"/></th>
                                                <th><fmt:message key="test.complexity"/></th>
                                                <th><fmt:message key="test.expirationDate"/></th>
                                                <th><fmt:message key="test.maxAttemptNumber"/></th>
                                                <th><fmt:message key="table.actions"/></th>
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
                                                              <eslib:datetime-formatter
                                                                      datetime="${test.expirationDate}"/>
                                                          </c:if>
                                                      </td>
                                                      <td>${test.maxAttemptNumber}</td>
                                                      <td>
                                                          <c:if test="${test.isAvailable}">
                                                                 <div class="d-flex flex-column">
                                                                     <button type="button"
                                                                             class="btn text-success btn-link btn-rounded btn-sm fw-bold"
                                                                             data-mdb-toggle="modal"
                                                                             data-mdb-target="#testConfirmationModal${test.uuid}"
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
                                                                  modalId="${test.uuid}"
                                                                  testingUrl="${pageContext.request.contextPath}/students/tests/testing?uuid=${test.uuid}"
                                                                  testDuration="${test.duration}"
                                                                  testMaxAttemptNumber="${test.maxAttemptNumber}"
                                                                  testCurrentAttemptNumber="${test.currentAttemptNumber}"/>
                                                          </c:if>
                                                          <c:if test="${not test.isAvailable}">
                                                              <div class="text-center">
                                                                  <fmt:message key="test.unavailable"/>
                                                              </div>
                                                          </c:if>
                                                      </td>
                                                  </tr>
                                                 </c:forEach>
                                            </tbody>
                                        </table>
                                  </c:if>
                              </div>
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
