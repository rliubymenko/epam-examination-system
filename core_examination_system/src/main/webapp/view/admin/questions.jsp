<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Questions</title>
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
                searchHeaderNames="${['table.tests']}">
            <jsp:attribute name="body">
                  <c:forEach var="question" items="${pageData.items}" varStatus="counter">
                      <tr>
                          <td>${counter.count}</td>
                          <td>
                              <c:choose>
                                 <c:when test="${question.type == 'text'}">
                                     <span class="badge badge-success rounded-pill d-inline">
                                        <fmt:message key="question.text"/>
                                     </span>
                                 </c:when>
                                 <c:when test="${question.type == 'numerical'}">
                                    <span class="badge badge-success rounded-pill d-inline">
                                        <fmt:message key="question.numerical"/>
                                     </span>
                                 </c:when>
                                  <c:when test="${question.type == 'true_false'}">
                                    <span class="badge badge-success rounded-pill d-inline">
                                        <fmt:message key="question.true_false"/>
                                     </span>
                                 </c:when>
                                  <c:when test="${question.type == 'single_choice'}">
                                    <span class="badge badge-info rounded-pill d-inline">
                                        <fmt:message key="question.single_choice"/>
                                     </span>
                                 </c:when>
                                  <c:when test="${question.type == 'multiple_choice'}">
                                    <span class="badge badge-info rounded-pill d-inline">
                                        <fmt:message key="question.multiple_choice"/>
                                     </span>
                                 </c:when>
                              </c:choose>
                          </td>
                          <td>${question.content}</td>
                          <td>${question.description}</td>
                          <td>${question.test.name}</td>
                          <td>
                              <div class="d-flex flex-column">
                                  <a href="${pageContext.request.contextPath}/admins/questions/question?uuid=${question.uuid}"
                                     type="button"
                                     class="btn btn-link btn-rounded btn-sm fw-bold"
                                     data-mdb-ripple-color="dark">
                                      <fmt:message key="table.edit"/>
                                  </a>
                                  <button type="button"
                                          class="btn text-danger btn-link btn-rounded btn-sm fw-bold"
                                          data-mdb-toggle="modal"
                                          data-mdb-target="#deleteModal"
                                          data-mdb-ripple-color="dark"
                                  >
                                      <fmt:message key="table.delete"/>
                                  </button>
                              </div>
                              <es:deletionModal modalId="deleteModal"
                                                deletionMessage="question.deletion_message"
                                                deletionUrl="${pageContext.request.contextPath}/admins/questions/question/delete?uuid=${question.uuid}"
                              />
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
