package com.epam.examinationsystem.core.datatable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Filters extracted from a query to filter data in a table.
 */
public class DataTableRequest {

    private int currentPage;
    private int pageSize;
    private String sort;
    private String order;
    private String searchUuid;
    private String searchQuery;

    public DataTableRequest(int currentPage, int pageSize, String sort, String order, String searchUuid, String searchQuery) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.sort = sort;
        this.order = order;
        this.searchUuid = searchUuid;
        this.searchQuery = searchQuery;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchUuid() {
        return searchUuid;
    }

    public void setSearchUuid(String searchUuid) {
        this.searchUuid = searchUuid;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sort", sort)
                .append("order", order)
                .append("currentPage", currentPage)
                .append("pageSize", pageSize)
                .append("foreignUuid", searchUuid)
                .append("searchQuery", searchQuery)
                .toString();
    }
}
