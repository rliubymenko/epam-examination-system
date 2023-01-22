package com.epam.examinationsystem.core.dto.pageable;

import com.epam.examinationsystem.core.dto.AbstractDto;

import java.util.*;

public class PageResponseDto<DTO extends AbstractDto> {

    private int currentPage;
    private int pageSize;
    private long totalPageSize;
    private long itemsSize;
    private List<DTO> items;
    private final int[] pageSizeItems;
    private boolean showFirst;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showLast;
    private String sort;
    private String order;
    private long currentShowFromEntries;
    private long currentShowToEntries;
    private List<Map<UUID, String>> dataForSearch;
    private Map<UUID, String> currentDataForSearch;

    public PageResponseDto() {
        this.currentPage = 0;
        this.pageSize = 10;
        this.totalPageSize = 0;
        this.itemsSize = 0;
        this.items = Collections.emptyList();
        this.dataForSearch = Collections.emptyList();
        this.currentDataForSearch = new HashMap<>();
        this.pageSizeItems = new int[]{10, 25, 50, 100};
        this.showFirst = false;
        this.showPrevious = false;
        this.showNext = false;
        this.showLast = false;
    }

    public void initPaginationState(int page) {
        this.showFirst = page != 1;
        this.showLast = page != totalPageSize;
        this.showNext = page != totalPageSize;
        this.showPrevious = page - 1 != 0;
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

    public long getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(long totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public long getItemsSize() {
        return itemsSize;
    }

    public void setItemsSize(long itemsSize) {
        this.itemsSize = itemsSize;
    }

    public List<DTO> getItems() {
        return items;
    }

    public void setItems(List<DTO> items) {
        this.items = items;
    }

    public int[] getPageSizeItems() {
        return pageSizeItems;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowLast() {
        return showLast;
    }

    public void setShowLast(boolean showLast) {
        this.showLast = showLast;
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

    public long getCurrentShowFromEntries() {
        return currentShowFromEntries;
    }

    public void setCurrentShowFromEntries(long currentShowFromEntries) {
        this.currentShowFromEntries = currentShowFromEntries;
    }

    public long getCurrentShowToEntries() {
        return currentShowToEntries;
    }

    public void setCurrentShowToEntries(long currentShowToEntries) {
        this.currentShowToEntries = currentShowToEntries;
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
}
