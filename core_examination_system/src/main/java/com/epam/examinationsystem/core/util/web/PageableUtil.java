package com.epam.examinationsystem.core.util.web;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AbstractDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

public class PageableUtil {

    public static final String DEFAULT_SORT_VALUE = "created";
    public static final String DEFAULT_ORDER_VALUE = "desc";
    private static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_SIZE_NUMBER = 10;
    public static final String DEFAULT_FOREIGN_UUID = "-1";
    public static final String DEFAULT_SEARCH_QUERY = "";

    private PageableUtil() {
    }

    public static DataTableRequest extractPageableData(HttpServletRequest request, List<HeaderName> headerNames) {
        String page = request.getParameter(Parameter.PAGE);
        String size = request.getParameter(Parameter.SIZE);
        String sort = request.getParameter(Parameter.SORT);
        String order = request.getParameter(Parameter.ORDER);
        String searchQuery = request.getParameter(Parameter.SEARCH_QUERY);
        String foreignUuid = request.getParameter(Parameter.SEARCH_UUID);
        int pageNumber = StringUtils.isNumeric(page) ? Integer.parseInt(page) : DEFAULT_PAGE_NUMBER;
        int sizeNumber = StringUtils.isNumeric(size) ? Integer.parseInt(size) : DEFAULT_SIZE_NUMBER;
        String orderString = StringUtils.isNotBlank(order) ? order.strip() : DEFAULT_ORDER_VALUE;
        String foreignUuidString = StringUtils.isNotBlank(foreignUuid) ? foreignUuid.strip() : DEFAULT_FOREIGN_UUID;
        String searchQueryString = StringUtils.isNotBlank(searchQuery) ? searchQuery.strip() : DEFAULT_SEARCH_QUERY;
        String sortString = extractSortStringDbName(sort, headerNames);
        return new DataTableRequest(pageNumber, sizeNumber, sortString, orderString, foreignUuidString, searchQueryString);
    }

    public static <ENTITY extends AbstractEntity, DTO extends AbstractDto> DataTableResponse<DTO> calculatePageableData(
            DataTableRequest request, CommonDao<ENTITY> dao) throws DaoException {

        DataTableResponse<DTO> response = new DataTableResponse<>();
        long count = dao.count(request);
        long entriesFrom = ((long) (request.getCurrentPage() - 1) * request.getPageSize()) + 1;
        long entriesTo = Math.min((long) request.getCurrentPage() * request.getPageSize(), count);
        long totalPageSize;
        if (count % request.getPageSize() == 0) {
            totalPageSize = count / request.getPageSize();
        } else {
            totalPageSize = count / request.getPageSize() + 1;
        }
        response.setEntriesFrom(entriesFrom);
        response.setEntriesTo(entriesTo);
        response.setTotalPageSize(totalPageSize);
        response.setEntitiesSize(count);
        response.setSearchQuery(request.getSearchQuery());
        return response;
    }

    private static String extractSortStringDbName(String sort, List<HeaderName> headerNames) {
        String sortString = StringUtils.isNotBlank(sort) ? sort.strip() : DEFAULT_SORT_VALUE;
        if (!sortString.equals(DEFAULT_SORT_VALUE)) {
            Optional<String> dbName = headerNames.stream()
                    .filter(headerName -> headerName.viewName() != null && headerName.viewName().equals(sortString))
                    .map(HeaderName::dbName)
                    .findFirst();
            if (dbName.isPresent()) {
                return dbName.get();
            }
        }
        return DEFAULT_SORT_VALUE;
    }
}
