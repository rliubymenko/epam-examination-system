package com.epam.examinationsystem.core.dto.pageable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class HeaderData {

    private String headerName;
    private Boolean active;
    private Boolean isSortable;
    private String sort;
    private String order;

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getIsSortable() {
        return isSortable;
    }

    public void setIsSortable(Boolean isSortable) {
        this.isSortable = isSortable;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("headerName", headerName)
                .append("active", active)
                .append("isSortable", isSortable)
                .append("sort", sort)
                .append("order", order)
                .toString();
    }
}
