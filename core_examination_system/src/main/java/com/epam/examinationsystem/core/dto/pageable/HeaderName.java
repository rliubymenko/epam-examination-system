package com.epam.examinationsystem.core.dto.pageable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public record HeaderName(String columnName, Boolean isSortable, String viewName, String dbName) {

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("columnName", columnName)
                .append("isSortable", isSortable)
                .append("viewName", viewName)
                .append("dbName", dbName)
                .toString();
    }
}
