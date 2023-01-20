<%@ tag pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="modalId" required="true" type="java.lang.String" %>
<%@ attribute name="testingUrl" required="true" type="java.lang.String" %>
<%@ attribute name="testDuration" required="true" type="java.lang.String" %>
<%@ attribute name="testMaxAttemptNumber" required="true" type="java.lang.String" %>
<%@ attribute name="testCurrentAttemptNumber" required="true" type="java.lang.String" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<div
        class="modal fade"
        id="testConfirmationModal${modalId}"
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
                    <c:if test="${testMaxAttemptNumber >= testCurrentAttemptNumber + 1}">
                        <p class="mb-2">
                            <fmt:message key="test.you_will_have"/> ${testDuration} <fmt:message
                                key="test.to_complete_test"/>
                        </p>
                        <p class="mb-2">
                            <fmt:message key="test.please_note_that_there_are"/> ${testMaxAttemptNumber} <fmt:message
                                key="test.attempts_to_pass_this_test"/>
                        </p>
                        <p class="mb-2">
                            <fmt:message key="test.passing_result_message"/>
                        </p>
                        <p class="mb-2">
                            <fmt:message key="user_test.number_of_current_attempt"/>: ${testCurrentAttemptNumber + 1}
                        </p>
                    </c:if>
                    <c:if test="${testMaxAttemptNumber < testCurrentAttemptNumber + 1}">
                        <p class="mb-2">
                            <fmt:message key="test.attempts_exhausted"/>
                        </p>
                    </c:if>
                </div>
            </div>
            <div class="modal-footer">
                <c:if test="${testMaxAttemptNumber < testCurrentAttemptNumber + 1}">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                        <fmt:message key="table.close"/>
                    </button>
                </c:if>
                <c:if test="${testMaxAttemptNumber >= testCurrentAttemptNumber + 1}">
                    <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                        <fmt:message key="table.close"/>
                    </button>
                    <a href="${testingUrl}"
                       type="button" class="btn btn-success">
                        <fmt:message key="test.start"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
