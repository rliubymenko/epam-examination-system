<%@ tag pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="headerDataList" required="true" type="java.util.List" %>
<%@ attribute name="cardHeader" required="true" type="java.lang.String" %>
<%@ attribute name="allowCreate" required="true" type="java.lang.String" %>
<%@ attribute name="createNewItemUrl" required="false" type="java.lang.String" %>
<%@ attribute name="pageData" required="true" type="com.epam.examinationsystem.core.dto.pageable.PageResponseDto" %>
<%@ attribute name="body" fragment="true" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle" var="locale"/>

<fmt:message bundle="${locale}" key="table.pagination_showing" var="showing"/>
<fmt:message bundle="${locale}" key="table.pagination_to" var="to"/>
<fmt:message bundle="${locale}" key="table.pagination_of" var="of"/>
<fmt:message bundle="${locale}" key="table.pagination_entries" var="entries"/>

<c:set var="dataPage" scope="session" value="${pageData.currentPage}"/>
<c:set var="dataSize" scope="session" value="${pageData.pageSize}"/>
<c:set var="dataSort" scope="session" value="${pageData.sort}"/>
<c:set var="dataOrder" scope="session" value="${pageData.order}"/>
<c:set var="currentUrl" value="${requestScope['jakarta.servlet.forward.request_uri']}"/>

<div class="d-flex justify-content-center pt-4">
    <div class="flex-grow-0">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <fmt:message key="${cardHeader}" bundle="${locale}"/>
                    </div>
                    <c:if test="${allowCreate}">
                        <div class="d-flex justify-content-end align-items-start">
                            <a class="btn btn-sm fw-bold btn-outline-success btn-rounded"
                               data-mdb-ripple-color="dark"
                               href="${pageContext.request.contextPath}${createNewItemUrl}">
                                <fmt:message key="table.create" bundle="${locale}"/>
                            </a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="card-body">
                <table class="table table-hover">
                    <thead class="table-head">
                    <tr>
                        <c:forEach items="${headerDataList}" var="columnHeader">
                            <th>
                                <c:if test="${columnHeader.isSortable}">
                                    <span>
                                        <fmt:message key="${columnHeader.headerName}" bundle="${locale}"/>
                                    </span>
                                    <c:if test="${columnHeader.order == 'desc'}">
                                        <a href="#" type="button"
                                           onclick="runSort('${columnHeader.sort}', '${dataOrder}', '${dataPage}', '${dataSize}')">
                                            <i class="fa fa-sort-desc float-end sort" aria-hidden="true"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${columnHeader.order == 'asc'}">
                                        <a href="#" type="button"
                                           onclick="runSort('${columnHeader.sort}', '${dataOrder}', '${dataPage}', '${dataSize}')">
                                            <i class="fa fa-sort-asc float-end sort" aria-hidden="true"></i>
                                        </a>
                                    </c:if>
                                </c:if>
                                <c:if test="${not columnHeader.isSortable}">
                                    <span>
                                        <fmt:message key="${columnHeader.headerName}" bundle="${locale}"/>
                                    </span>
                                    <a href="#" type="button">
                                        <i class="fa fa-sort float-end sort" aria-hidden="true"></i>
                                    </a>
                                </c:if>
                            </th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <jsp:invoke fragment="body"/>
                    </tbody>
                </table>

                <div class="d-flex justify-content-between">
                    <div class="d-flex justify-content-start align-items-start">
                        <div class="ps-3">
                            ${showing} ${pageData.currentShowFromEntries} ${to} ${pageData.currentShowToEntries} ${of} ${pageData.itemsSize} ${entries}
                        </div>
                    </div>
                    <div class="d-flex justify-content-center align-items-center">
                        <ul class="pagination pagination-circle justify-content-center">
                            <li class="${pageData.showFirst ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link " href="#"
                                   onclick="runSortWithPagination('${dataSort}', '${dataOrder}', 1, '${pageData.pageSize}', 0)"
                                   title="<fmt:message key="table.first_page" bundle="${locale}"/>"><i
                                        class="fa fa-fast-backward"></i></a>
                            </li>
                            <li class="${pageData.showPrevious ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runSortWithPagination('${dataSort}', '${dataOrder}','${pageData.currentPage}', '${pageData.pageSize}', -1)"
                                   title="<fmt:message key="table.previous_page" bundle="${locale}"/>"><i
                                        class="fa fa-backward"></i></a>
                            </li>
                            <li class="page-item disabled">
                                <a class="page-link" href="#">${pageData.currentPage}</a>
                            </li>
                            <li class="${pageData.showNext ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runSortWithPagination('${dataSort}', '${dataOrder}', '${pageData.currentPage}', '${pageData.pageSize}', 1)"
                                   title="<fmt:message key="table.next_page" bundle="${locale}"/>"><i
                                        class="fa fa-forward"></i></a>
                            </li>
                            <li class="${pageData.showLast ? 'page-item' : 'page-item disabled'}">
                                <a class="page-link" href="#"
                                   onclick="runSortWithPagination('${dataSort}', '${dataOrder}', '${pageData.totalPageSize}', '${pageData.pageSize}', 0)"
                                   title="<fmt:message key="table.last_page" bundle="${locale}"/>"><i
                                        class="fa fa-fast-forward"></i></a>
                            </li>
                        </ul>
                    </div>
                    <div class="d-flex justify-content-end align-items-start">
                        <button id="dropdownSizeButton"
                                type="button"
                                title="<fmt:message key="table.page_size" bundle="${locale}"/>"
                                class="btn btn-rounded dropdown-toggle"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false">
                            ${pageData.pageSize}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownSizeButton">
                            <c:forEach var="size" items="${pageData.pageSizeItems}">
                                <li>
                                    <a class="dropdown-item" href="#"
                                       onclick="runSortWithPagination('${dataSort}', '${dataOrder}', 1, ${size}, 0)">
                                            ${size}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <a class="page-link btn btn-secondary px-3" href="#"
                           onclick="runSortWithPagination('${dataSort}', '${dataOrder}', '${pageData.currentPage}', '${pageData.pageSize}', 0)"
                           title="<fmt:message key="table.refresh_page" bundle="${locale}"/>"><i
                                class="fa fa-refresh"></i></a>
                        <a class="page-link btn btn-secondary pe-3" href="#"
                           onclick="runSortWithPagination('${dataSort}', '${dataOrder}', 1, 10, 0)"
                           title="<fmt:message key="table.reset_page" bundle="${locale}"/>"><i class="fa fa-trash"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form id="personalSearch" action="${currentUrl}" method="GET" novalidate>
        <input type="submit" id="personalSearchSubmit" hidden/>
    </form>
</div>
