<%@ tag pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="question" required="true"
              type="com.epam.examinationsystem.core.dto.StudentTestDto.QuestionForStudentTestDto" %>
<%@ attribute name="questionId" required="true" type="java.lang.String" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<div class="container">
    <div class="row">
        <ul class="list-unstyled ps-0">
            <li class="text-start"><i class="fas fa-question-circle me-2 text-warning"></i>
                <c:choose>
                    <c:when test="${question.type == 'text'}">
                        <small>
                            <fmt:message key="question.text_hint"/>
                        </small>
                    </c:when>
                    <c:when test="${question.type == 'numerical'}">
                        <fmt:message key="question.numerical_hint"/>
                    </c:when>
                    <c:when test="${question.type == 'true_false'}">
                        <fmt:message key="question.true_false_hint"/>
                    </c:when>
                    <c:when test="${question.type == 'single_choice'}">
                        <fmt:message key="question.single_choice_hint"/>
                    </c:when>
                    <c:when test="${question.type == 'multiple_choice'}">
                        <fmt:message key="question.multiple_choice_hint"/>
                    </c:when>
                </c:choose>
            </li>
        </ul>

        <c:if test="${not empty question.description}">
            <div class="note text-start note-success mb-3">
                    ${question.description}
            </div>
        </c:if>
    </div>
    <div class="row pb-3">
        <div class="col-12 px-0 test-start">
            <div class="card">
                <div class="card-body">
                    <h5 class="text-dark text-start fe-bold p-1">
                        ${question.content}
                    </h5>
                </div>
            </div>
        </div>
    </div>
    <div class="row pb-3">
        <c:choose>
            <c:when test="${question.type == 'text'}">
                <div class="col-12 px-0 form-outline">
                    <textarea id="answer${questionId}"
                              class="form-control form-control-lg"
                              rows="4"
                              name="answer_uuid${question.answers[0].uuid}"
                    ></textarea>
                    <label class="form-label" for="answer${questionId}">
                        <fmt:message key="question.answer"/>
                    </label>
                </div>
            </c:when>
            <c:when test="${question.type == 'numerical'}">
                <div class="col-12 px-0 form-outline">
                    <input
                            type="number"
                            id="answer${questionId}"
                            name="answer_uuid${question.answers[0].uuid}"
                            class="form-control form-control-lg"
                    />
                    <label class="form-label" for="answer${questionId}">
                        <fmt:message key="question.answer"/>
                    </label>
                </div>
            </c:when>
            <c:when test="${question.type == 'true_false'}">
                <div class="col-12 px-0 form-outline">
                    <label class="form-check-label" for="answer${questionId}">
                        <fmt:message key="question.answer"/>
                    </label>
                    <input
                            id="answer${questionId}"
                            class="form-check-input"
                            type="checkbox"
                            name="answer_uuid${question.answers[0].uuid}"
                    />
                </div>
            </c:when>
            <c:when test="${question.type == 'single_choice'}">
                <c:forEach var="answer" items="${question.answers}" varStatus="answerStatus">
                    <div class="col-12 pb-3 d-flex justify-content-start">
                        <input id="choiceCheckbox${answerStatus.index}"
                               class="form-check-input"
                               name="choice_question_uuid${question.uuid}"
                               value="${answer.uuid}"
                               type="radio"/>
                        <label class="form-check-label" for="choiceCheckbox${answerStatus.index}">
                                ${answer.content}
                        </label>
                    </div>
                </c:forEach>
            </c:when>
            <c:when test="${question.type == 'multiple_choice'}">
                <c:forEach var="answer" items="${question.answers}" varStatus="answerStatus">
                    <div class="col-12 pb-3 d-flex justify-content-start">
                        <input id="choiceCheckbox${answerStatus.index}"
                               class="form-check-input"
                               name="choice_question_uuid${question.uuid}"
                               value="${answer.uuid}"
                               type="checkbox"/>
                        <label class="form-check-label" for="choiceCheckbox${answerStatus.index}">
                                ${answer.content}
                        </label>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </div>
</div>
