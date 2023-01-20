<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<head>
    <title>New Answer</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    <script src="${pageContext.request.contextPath}/static/js/newAnswer.js"></script>
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>


<div class="row d-flex justify-content-center pt-4">
    <div class="col-6">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <fmt:message key="answer.new"/>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <a href="${pageContext.request.contextPath}/admins/answers"
                           type="button"
                           class="btn btn-success">
                            <fmt:message key="edit.go_back_to_table"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${pageContext.request.contextPath}/admins/answers/answer/new">

                    <h6 class="text-warning mb-4">
                        <fmt:message key="answer.question"/>
                    </h6>
                    <hr>
                    <div class="mb-4">
                        <select id="questionId" aria-label="Question"
                                name="question_uuid"
                                class="${not empty inconsistencies && inconsistencies.contains('question') ?
                                'is-invalid form-select' :
                                'form-select'}">
                            >
                            <option value="-1" selected>
                                <fmt:message key="table.questions"/>
                            </option>
                            <c:forEach var="question" items="${questions}"
                                       varStatus="counter">
                                <option value="${question.uuid}">
                                    <c:choose>
                                        <c:when test="${question.type == 'text'}">
                                            Text
                                        </c:when>
                                        <c:when test="${question.type == 'numerical'}">
                                            Numerical
                                        </c:when>
                                        <c:when test="${question.type == 'true_false'}">
                                            True or false
                                        </c:when>
                                        <c:when test="${question.type == 'single_choice'}">
                                            Single choice
                                        </c:when>
                                        <c:when test="${question.type == 'multiple_choice'}">
                                            Multiple choice
                                        </c:when>
                                    </c:choose>
                                    <p>| ${question.content}</p>
                                </option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('question')}">
                                <fmt:message key="question.choose_type"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="text-center">
                        <small class="text-warning">
                            * <fmt:message key="question.valid_answers"/>
                        </small>
                    </div>

                    <div id="textAnswerDiv" hidden>
                        <label class="form-label" for="textAnswer">
                            <fmt:message key="question.text"/>
                        </label>
                        <textarea id="textAnswer"
                                  rows="10"
                                  name="textAnswer"
                                  class="form-control form-control-lg"
                        ></textarea>
                    </div>

                    <div id="numericalAnswerDiv" hidden>
                        <label class="form-label" for="numericalAnswer">
                            <fmt:message key="question.numerical"/>
                        </label>
                        <input
                                type="number"
                                id="numericalAnswer"
                                name="numericalAnswer"
                                class="form-control form-control-lg"
                        />
                    </div>

                    <div id="singleChoiceLabel" hidden>
                        <fmt:message key="question.single_choice"/>
                    </div>

                    <div id="multipleChoiceLabel" hidden>
                        <fmt:message key="question.multiple_choice"/>
                    </div>

                    <div id="choiceToggler" data-toggle="buttons" hidden>
                        <div class="btn-group-justified checkboxChoice mt-4">
                            <div class="input-group">
                                <div class="input-group-text">
                                    <input id="choiceCheckbox_1"
                                           class="form-check-input"
                                           name="answers.choice"
                                           value="1"
                                           type="radio"
                                           checked
                                           aria-label="Button for following text input"/>
                                </div>

                                <textarea id="choiceTextArea_1"
                                          rows="4"
                                          name="answers.answer"
                                          class="form-control form-control-lg"
                                          aria-label="Single choice field"
                                ></textarea>
                                <div class="input-group-text">
                                    <button type="button"
                                            class="copyNodeBtnField btn btn-success">
                                        <i class="far fa-copy"></i>
                                    </button>

                                    <button type="button"
                                            class="removeNodeBtnField btn btn-danger"
                                            disabled>
                                        <i class="far fa-trash-alt"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button hidden id="addAnswerBtn" type="button"
                            class="btn btn-outline-success btn-rounded mt-4"
                            data-mdb-ripple-color="dark">
                        <fmt:message key="question.add_new_answer"/>
                    </button>

                    <button type="submit" class="btn btn-secondary btn-block mt-4">
                        <fmt:message key="edit.new"/>
                    </button>

                </form>
            </div>
        </div>
    </div>
</div>

<div class="fixed-bottom">
    <jsp:include page="/view/shared/footer.jsp"/>
</div>

</body>
</html>
