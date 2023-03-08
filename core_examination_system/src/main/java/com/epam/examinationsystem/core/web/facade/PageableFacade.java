package com.epam.examinationsystem.core.web.facade;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AbstractDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.util.web.PageableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Facade for DTO packages all necessary data to PageResponseDto.
 */
public class PageableFacade<DTO extends AbstractDto> {

    private final List<HeaderName> headerNames;
    private final PageResponseDto<DTO> pageResponseDto;

    public PageableFacade(DataTableRequest request, DataTableResponse<DTO> response, List<HeaderName> headerNames) {
        pageResponseDto = new PageResponseDto<>();
        pageResponseDto.setItems(response.getDtos());
        pageResponseDto.setDataForSearch(response.getDataForSearch());
        pageResponseDto.setCurrentDataForSearch(response.getCurrentDataForSearch());
        pageResponseDto.setCurrentPage(request.getCurrentPage());
        pageResponseDto.setPageSize(request.getPageSize());
        pageResponseDto.setSort(extractSortStringViewName(request.getSort(), headerNames));
        pageResponseDto.setOrder(request.getOrder());
        pageResponseDto.setItemsSize(response.getEntitiesSize());
        pageResponseDto.setTotalPageSize(response.getTotalPageSize());
        pageResponseDto.setCurrentShowFromEntries(response.getEntriesFrom());
        pageResponseDto.setCurrentShowToEntries(response.getEntriesTo());
        pageResponseDto.setSearchQuery(request.getSearchQuery());
        pageResponseDto.initPaginationState(request.getCurrentPage());
        this.headerNames = headerNames;
    }

    public PageResponseDto<DTO> getPageResponseDto() {
        return pageResponseDto;
    }

    public List<HeaderData> getHeaderDataList() {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : headerNames) {
            HeaderData headerData = new HeaderData();
            headerData.setHeaderName(headerName.columnName());
            headerData.setIsSortable(headerName.isSortable());
            headerData.setSort(headerName.viewName());
            if (pageResponseDto.getSort().equalsIgnoreCase(headerName.viewName())) {
                headerData.setActive(true);
                headerData.setOrder(pageResponseDto.getOrder());
            } else {
                headerData.setActive(false);
                headerData.setOrder(PageableUtil.DEFAULT_ORDER_VALUE);
            }
            headerDataList.add(headerData);
        }
        return headerDataList;
    }

    private static String extractSortStringViewName(String sort, List<HeaderName> headerNames) {
        if (!sort.equals(PageableUtil.DEFAULT_SORT_VALUE)) {
            return headerNames.stream()
                    .filter(headerName -> headerName.dbName() != null && headerName.dbName().contains(sort))
                    .map(HeaderName::viewName)
                    .findFirst()
                    .get();
        }
        return PageableUtil.DEFAULT_SORT_VALUE;
    }
}
