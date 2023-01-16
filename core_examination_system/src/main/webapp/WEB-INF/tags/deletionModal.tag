<%@ tag pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="modalId" required="true" type="java.lang.String" %>
<%@ attribute name="deletionUrl" required="true" type="java.lang.String" %>
<%@ attribute name="deletionMessage" required="false" type="java.lang.String" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle"/>

<div
        class="modal fade"
        id="${modalId}"
        data-mdb-backdrop="static"
        data-mdb-keyboard="false"
        tabindex="-1"
        aria-labelledby="staticBackdropLabel"
        aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="staticBackdropLabel">
                    <fmt:message key="table.deletion"/>
                </h3>
                <button type="button" class="btn-close" data-mdb-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="text-center">
                    <h6 class="text-warning">
                        <fmt:message key="table.deletion_warning_message"/>
                        <c:if test="${not empty deletionMessage}">
                            <fmt:message key="${deletionMessage}"/>
                        </c:if>
                    </h6>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">
                    <fmt:message key="table.close"/>
                </button>
                <a href="${deletionUrl}"
                   type="button" class="btn btn-danger">
                    <fmt:message key="table.delete"/>
                </a>
            </div>
        </div>
    </div>
</div>
