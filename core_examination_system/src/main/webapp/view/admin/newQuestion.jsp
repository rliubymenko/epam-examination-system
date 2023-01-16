<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<html>
<head>
    <title>New Question</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="/view/shared/static.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/home.css">
    <script src="${pageContext.request.contextPath}/static/js/newQuestion.js"></script>
</head>
<body>

<jsp:include page="/view/shared/header.jsp"/>

<c:set var="pathWithTestUuid" scope="session" value="${pageContext.request.contextPath}/admins/questions/question/new?test_uuid=${test_uuid}"/>
<c:set var="pathWithoutTestUuid" scope="session" value="${pageContext.request.contextPath}/admins/questions/question/new"/>

<div class="row d-flex justify-content-center pt-4">
    <div class="col-6">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <fmt:message key="question.new"/>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <a href="${pageContext.request.contextPath}/admins/questions"
                           type="button"
                           class="btn btn-success">
                            <fmt:message key="edit.go_back_to_table"/>
                        </a>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form method="post"
                      action="${empty test_uuid ? pathWithoutTestUuid : pathWithTestUuid}">

                    <div class="form-outline mb-4">
                        <textarea id="content"
                                  rows="9"
                                  name="content"
                                  class="${not empty inconsistencies && inconsistencies.contains('content') ?
                                'is-invalid form-control form-control-lg' :
                                'form-control form-control-lg'}"
                        ></textarea>
                        <label class="form-label" for="content">
                            <fmt:message key="question.content"/>
                        </label>
                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('content')}">
                                <fmt:message key="question.invalid_content"/>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-outline mb-4">
                        <textarea id="description"
                                  class="form-control form-control-lg"
                                  rows="5"
                                  name="description"
                        ></textarea>
                        <label class="form-label" for="description">
                            <fmt:message key="question.description"/>
                        </label>
                    </div>

                    <div class="mb-4">
                        <small><fmt:message key="question.type"/></small>
                        <select id="questionType" aria-label="question" name="type"
                                class="${not empty inconsistencies && inconsistencies.contains('type') ?
                                'is-invalid form-select' :
                                'form-select'}">
                            <option value="-1" selected>
                                <fmt:message key="question.types"/>
                            </option>
                            <option value="text">
                                <fmt:message key="question.text"/>
                            </option>
                            <option value="numerical">
                                <fmt:message key="question.numerical"/>
                            </option>
                            <option value="true_false">
                                <fmt:message key="question.true_false"/>
                            </option>
                            <option value="single_choice">
                                <fmt:message key="question.single_choice"/>
                            </option>
                            <option value="multiple_choice">
                                <fmt:message key="question.multiple_choice"/>
                            </option>
                        </select>

                        <div class="invalid-feedback">
                            <c:if test="${not empty inconsistencies && inconsistencies.contains('type')}">
                                <fmt:message key="question.choose_type"/>
                            </c:if>
                        </div>
                    </div>

                    <c:if test="${empty test_uuid}">
                        <div class="mb-4">
                            <small><fmt:message key="question.test"/></small>
                            <select id="testId" aria-label="test" name="test_uuid"
                                    class="${not empty inconsistencies && inconsistencies.contains('test') ?
                                'is-invalid form-select' :
                                'form-select'}">
                                <option value="-1" selected>
                                    <fmt:message key="question.tests"/>
                                </option>
                                <c:forEach var="test" items="${tests}">
                                    <option value="${test.uuid}">
                                            ${test.name}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                <c:if test="${not empty inconsistencies && inconsistencies.contains('test')}">
                                    <fmt:message key="question.choose_test"/>
                                </c:if>
                            </div>
                        </div>
                    </c:if>

                    <button type="submit" class="btn btn-secondary btn-block">
                        <fmt:message key="edit.new"/>
                    </button>

                    <button id="modalBtn" type="button" data-mdb-toggle="modal" data-mdb-target="#answerModal"
                            class="btn btn-secondary btn-block">
                        <fmt:message key="question.add_new_answers"/>
                    </button>

                    <div class="modal fade" id="answerModal" tabindex="-1" aria-labelledby="answerModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                                <div class="modal-header text-center">
                                    <h4 class="modal-title" id="answerModalLabel">
                                        <fmt:message key="question.answers"/>
                                    </h4>
                                    <button type="button"
                                            class="btn-close"
                                            data-mdb-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div id="answerModalBody" class="modal-body">
                                    <div class="text-center">
                                        <small class="text-warning">
                                            * <fmt:message key="question.valid_answers"/>
                                        </small>
                                    </div>

                                    <div id="nothingSelectedDiv" class="text-center">
                                        <fmt:message key="question.empty_answer_type"/>
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

                                    <div id="trueFalseDiv" hidden>
                                        <label class="form-check-label" for="trueFalse">
                                            <fmt:message key="question.true_false"/>
                                        </label>
                                        <input
                                                id="trueFalse"
                                                class="form-check-input"
                                                type="checkbox"
                                                name="trueFalseAnswer"
                                                checked
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
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-secondary">
                                        <fmt:message key="question.save_question_answer"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
