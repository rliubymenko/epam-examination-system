package com.epam.examinationsystem.core.datatable;

import com.epam.examinationsystem.core.dto.AbstractDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;

public class DataTableResponse<DTO extends AbstractDto> {

    private List<DTO> dtos;
    private long entitiesSize;
    private long entriesFrom;
    private long entriesTo;
    private long totalPageSize;
    private List<Map<UUID, String>> dataForSearch;
    private Map<UUID, String> currentDataForSearch;

    public DataTableResponse() {
        dtos = Collections.emptyList();
        dataForSearch = new ArrayList<>();
        currentDataForSearch = new HashMap<>();
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

    public List<Map<UUID, String>> getDataForSearch() {
        return dataForSearch;
    }

    public void setDataForSearch(List<Map<UUID, String>> dataForSearch) {
        this.dataForSearch = dataForSearch;
    }

    public Map<UUID, String> getCurrentDataForSearch() {
        return currentDataForSearch;
    }

    public void setCurrentDataForSearch(Map<UUID, String> currentDataForSearch) {
        this.currentDataForSearch = currentDataForSearch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dtos", dtos)
                .append("entitiesSize", entitiesSize)
                .append("entriesFrom", entriesFrom)
                .append("entriesTo", entriesTo)
                .append("totalPageSize", totalPageSize)
                .append("currentDataForSearch", currentDataForSearch)
                .toString();
    }
}
