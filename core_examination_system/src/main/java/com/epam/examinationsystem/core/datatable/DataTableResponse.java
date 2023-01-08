package com.epam.examinationsystem.core.datatable;

import com.epam.examinationsystem.core.dto.AbstractDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

public class DataTableResponse<DTO extends AbstractDto> {

    private List<DTO> dtos;
    private long entitiesSize;
    private long entriesFrom;
    private long entriesTo;
    private long totalPageSize;


    public DataTableResponse() {
        dtos = Collections.emptyList();
        entitiesSize = 0;
    }

    public List<DTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<DTO> dtos) {
        this.dtos = dtos;
    }

    public long getEntitiesSize() {
        return entitiesSize;
    }

    public void setEntitiesSize(long entitiesSize) {
        this.entitiesSize = entitiesSize;
    }

    public long getEntriesFrom() {
        return entriesFrom;
    }

    public void setEntriesFrom(long entriesFrom) {
        this.entriesFrom = entriesFrom;
    }

    public long getEntriesTo() {
        return entriesTo;
    }

    public void setEntriesTo(long entriesTo) {
        this.entriesTo = entriesTo;
    }

    public long getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(long totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dtos", dtos)
                .append("entitiesSize", entitiesSize)
                .append("entriesFrom", entriesFrom)
                .append("entriesTo", entriesTo)
                .append("totalPageSize", totalPageSize)
                .toString();
    }
}
