package com.epam.examinationsystem.core.util.web;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AbstractDto;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public final class PageableUtil {

    public static final String DEFAULT_SORT_VALUE = "uuid";
    public static final String DEFAULT_ORDER_VALUE = "desc";
    private static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_SIZE_NUMBER = 10;

    private PageableUtil() {
    }

    public static DataTableRequest extractPageableData(HttpServletRequest request) {
        String page = request.getParameter(Parameter.PAGE);
        String size = request.getParameter(Parameter.SIZE);
        String sort = request.getParameter(Parameter.SORT);
        String order = request.getParameter(Parameter.ORDER);
        int pageNumber = StringUtils.isNumeric(page) ? Integer.parseInt(page) : DEFAULT_PAGE_NUMBER;
        int sizeNumber = StringUtils.isNumeric(size) ? Integer.parseInt(size) : DEFAULT_SIZE_NUMBER;
        String sortString = StringUtils.isNotBlank(sort) ? sort.strip() : DEFAULT_SORT_VALUE;
        String orderString = StringUtils.isNotBlank(order) ? order.strip() : DEFAULT_ORDER_VALUE;
        return new DataTableRequest(pageNumber, sizeNumber, sortString, orderString);
    }

    public static <ENTITY extends AbstractEntity, DTO extends AbstractDto> DataTableResponse<DTO> calculatePageableData(
            DataTableRequest request, CommonDao<ENTITY> dao) throws DaoException {

        DataTableResponse<DTO> response = new DataTableResponse<>();
        long count = dao.count();
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
        return response;
    }
}
