package com.epam.examinationsystem.core.util.web;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
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
}
