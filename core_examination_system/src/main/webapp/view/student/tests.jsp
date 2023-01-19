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
                      </div>
                      <div
                              class="modal fade"
                              id="testConfirmationModal${counter.count}"
                              data-mdb-backdrop="static"
                              data-mdb-keyboard="false"
                              tabindex="-1"
                              aria-labelledby="staticBackdropLabel"
                              aria-hidden="true"
                      >
                          <div class="modal-dialog modal-lg">
                              <div class="modal-content">
                                  <div class="modal-header">
                                      <h3 class="modal-title" id="staticBackdropLabel">
                                          <fmt:message key="test.testing_confirmation_header"/>
                                      </h3>
                                      <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                              aria-label="Close"></button>
                                  </div>
                                  <div class="modal-body">
                                      <div class="text-center">
                                          <h6 class="text-warning">
                                              <fmt:message key="test.testing_warning_message"/>
                                          </h6>
                                          <p>
                                              <fmt:message key="test.you_will_have"/> ${test.duration} <fmt:message
                                                  key="test.to_complete_test"/>
                                          </p>
                                      </div>
                                  </div>
                                  <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                                          <fmt:message key="table.close"/>
                                      </button>
                                      <a href="${pageContext.request.contextPath}/students/tests/testing?uuid=${test.uuid}"
                                         type="button" class="btn btn-success">
                                          <fmt:message key="test.start"/>
                                      </a>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </td>
              </tr>
          </c:forEach>
    </jsp:attribute>
</es:datatable>

</body>
</html>
