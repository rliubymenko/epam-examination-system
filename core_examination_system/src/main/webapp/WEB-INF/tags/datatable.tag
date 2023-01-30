<%@ tag pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="es" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="headerDataList" required="true" type="java.util.List" %>
<%@ attribute name="cardHeader" required="true" type="java.lang.String" %>
<%@ attribute name="allowCreate" required="true" type="java.lang.String" %>
<%@ attribute name="createNewItemUrl" required="false" type="java.lang.String" %>
<%@ attribute name="searchHeaderNames" required="false" type="java.util.List" %>
<%@ attribute name="pageData" required="true" type="com.epam.examinationsystem.core.dto.pageable.PageResponseDto" %>
<%@ attribute name="body" fragment="true" %>


<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="i18n.bundle" var="locale"/>

<fmt:message bundle="${locale}" key="table.pagination_showing" var="showing"/>
<fmt:message bundle="${locale}" key="table.pagination_to" var="to"/>
<fmt:message bundle="${locale}" key="table.pagination_of" var="of"/>
<fmt:message bundle="${locale}" key="table.pagination_entries" var="entries"/>

<c:set var="dataPage" scope="page" value="${pageData.currentPage}"/>
<c:set var="searchQuery" scope="page" value="${pageData.searchQuery}"/>
<c:set var="dataSize" scope="page" value="${pageData.pageSize}"/>
<c:set var="dataSort" scope="page" value="${pageData.sort}"/>
<c:set var="dataOrder" scope="page" value="${pageData.order}"/>
<c:set var="dataForSearch" scope="page" value="${pageData.dataForSearch}"/>
<c:set var="currentUrl" value="${requestScope['jakarta.servlet.forward.request_uri']}"/>
<c:forEach var="status" items="${pageData.currentDataForSearch}" varStatus="currentDataForSearchStatus">
    <c:if test="${currentDataForSearchStatus.count eq 1}">
        <c:set var="currentDataSearch" scope="page" value="${status}"/>
    </c:if>
</c:forEach>


<div class="d-flex justify-content-center px-5">
    <div class="row">
        <div class="card bg-light border border-primary shadow-0">
            <div class="card-header">
                <div class="d-flex justify-content-between mb-1">
                    <div class="d-flex justify-content-start align-items-start">
                        <div class="text-nowrap">
                            <fmt:message key="${cardHeader}" bundle="${locale}"/>
                        </div>
                        <div class="input-group ps-3">
                            <div class="form-outline">
                                <input id="searchId" name="search_query"
                                       value="${not empty searchQuery ? searchQuery: ''}"
                                       type="search" class="form-control"/>
                                <label class="form-label" for="searchId">
                                    <fmt:message key="table.search" bundle="${locale}"/>
                                </label>
                            </div>
                            <button type="button"
                                    class="btn btn-sm btn-primary fw-bold"
                                    onclick="runSearch('${dataSort}', '${dataOrder}', '${dataPage}', '${dataSize}')"
                            >
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <div class="d-flex justify-content-center mx-auto flex-row align-items-center">
                        <ul class="pagination pagination-circle justify-content-center mb-0">
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
                    <c:if test="${not empty dataForSearch}">
                        <div class="d-flex px-2 justify-content-end align-items-start">
                            <c:forEach var="searchData" items="${dataForSearch}" varStatus="searchDataStatus">
                                <select id="select_${searchDataStatus.index}"
                                        aria-label="search"
                                        class="form-select"
                                        style="width: 10rem"
                                        onchange="runSortByCriteria(event, '${dataSort}', '${dataOrder}', '${dataPage}', '${dataSize}')"
                                >
                                    <option value="-1" ${empty pageData.currentDataForSearch ? 'selected' : ''}>
                                        <fmt:message key="${searchHeaderNames[searchDataStatus.index]}"
                                                     bundle="${locale}"/>
                                    </option>
                                    <c:forEach var="dataMap" items="${searchData}" varStatus="dataMapStatus">
                                        <option ${not empty pageData.currentDataForSearch && currentDataSearch.key == dataMap.key ? 'selected' : ''}
                                                value="${dataMap.key}">
                                                ${dataMap.value}
                                        </option>
                                    </c:forEach>
                                </select>
                            </c:forEach>
                        </div>
                    </c:if>
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
                    <c:if test="${empty pageData.items}">
                        <div class="text-center">
                            <h1>
                                <fmt:message key="table.empty_body" bundle="${locale}"/>
                            </h1>
                        </div>
                    </c:if>
                    <c:if test="${not empty pageData.items}">
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
                    </c:if>
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
                           onclick="resetAll()"
                           title="<fmt:message key="table.reset_page" bundle="${locale}"/>"><i class="fa fa-trash"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form id="personalSearch" action="${currentUrl}" method="GET" novalidate>
        <c:if test="${not empty pageData.currentDataForSearch}">
            <input id="searchIdInput" type="hidden" name="search_uuid" value="${currentDataSearch.key}"/>
        </c:if>
        <input type="submit" id="personalSearchSubmit" hidden/>
    </form>
</div>
