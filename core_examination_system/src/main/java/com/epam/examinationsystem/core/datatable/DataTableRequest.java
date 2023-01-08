package com.epam.examinationsystem.core.datatable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DataTableRequest {

    private int currentPage;
    private int pageSize;
    private String sort;
    private String order;

    public DataTableRequest(int currentPage, int pageSize, String sort, String order) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.sort = sort;
        this.order = order;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("sort", sort)
                .append("order", order)
                .append("currentPage", currentPage)
                .append("pageSize", pageSize)
                .toString();
    }
}
