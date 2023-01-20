<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>Answers</title>
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
          <c:forEach var="answer" items="${pageData.items}" varStatus="counter">
              <tr>
                  <td>${counter.count}</td>
                  <td>${answer.content}</td>
                  <td>
                      <c:choose>
                         <c:when test="${answer.isCorrect}">
                             <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="answer.correct_answer"/>
                             </span>
                         </c:when>
                         <c:otherwise>
                            <span class="badge badge-danger rounded-pill d-inline">
                                <fmt:message key="answer.wrong_answer"/>
                             </span>
                         </c:otherwise>
                      </c:choose>
                  </td>
                  <td>${answer.question.content}</td>
                  <td>
                      <c:choose>
                         <c:when test="${answer.question.type == 'text'}">
                             <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.text"/>
                             </span>
                         </c:when>
                         <c:when test="${answer.question.type == 'numerical'}">
                            <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.numerical"/>
                             </span>
                         </c:when>
                          <c:when test="${answer.question.type == 'true_false'}">
                            <span class="badge badge-success rounded-pill d-inline">
                                <fmt:message key="question.true_false"/>
                             </span>
                         </c:when>
                          <c:when test="${answer.question.type == 'single_choice'}">
                            <span class="badge badge-info rounded-pill d-inline">
                                <fmt:message key="question.single_choice"/>
                             </span>
                         </c:when>
                          <c:when test="${answer.question.type == 'multiple_choice'}">
                            <span class="badge badge-info rounded-pill d-inline">
                                <fmt:message key="question.multiple_choice"/>
                             </span>
                         </c:when>
                      </c:choose>
                  </td>
                  <td>
                      <div class="d-flex flex-column">
                          <a href="${pageContext.request.contextPath}/admins/answers/answer?uuid=${answer.uuid}"
                             type="button"
                             class="btn btn-link btn-rounded btn-sm fw-bold"
                             data-mdb-ripple-color="dark">
                              <fmt:message key="table.edit"/>
                          </a>
                          <button type="button"
                                  class="btn text-danger btn-link btn-rounded btn-sm fw-bold"
                                  data-mdb-toggle="modal"
                                  data-mdb-target="#deleteModal${counter.count}"
                                  data-mdb-ripple-color="dark"
                          >
                              <fmt:message key="table.delete"/>
                          </button>
                      </div>

                      <div
                              class="modal fade"
                              id="deleteModal${counter.count}"
                              data-mdb-backdrop="static"
                              data-mdb-keyboard="false"
                              tabindex="-1"
                              aria-labelledby="deletionLabel"
                              aria-hidden="true"
                      >
                          <div class="modal-dialog">
                              <div class="modal-content">
                                  <div class="modal-header">
                                      <h3 class="modal-title" id="deletionLabel">
                                          <fmt:message key="table.deletion"/>
                                      </h3>
                                      <button type="button" class="btn-close" data-mdb-dismiss="modal"
                                              aria-label="Close"></button>
                                  </div>
                                  <div class="modal-body">
                                      <div class="text-center">
                                          <h6 class="text-warning">
                                              <fmt:message key="table.deletion_warning_message"/>
                                          </h6>
                                      </div>
                                  </div>
                                  <div class="modal-footer">
                                      <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                                          <fmt:message key="table.close"/>
                                      </button>

                                      <a href="${pageContext.request.contextPath}/admins/answers/answer/delete?uuid=${answer.uuid}&question_uuid=${answer.question.uuid}"
                                         type="button"
                                         class="btn btn-danger"
                                      >
                                          <fmt:message key="table.delete"/>
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

<div class="pt-4">
    <jsp:include page="/view/shared/footer.jsp"/>
</div>

</body>
</html>
