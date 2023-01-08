package com.epam.examinationsystem.core.web.facade;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AbstractDto;
import com.epam.examinationsystem.core.dto.pageable.HeaderData;
import com.epam.examinationsystem.core.dto.pageable.HeaderName;
import com.epam.examinationsystem.core.dto.pageable.PageResponseDto;
import com.epam.examinationsystem.core.util.PageableUtil;

import java.util.ArrayList;
import java.util.List;

public class PageableFacade<DTO extends AbstractDto> {

    private final PageResponseDto<DTO> pageResponseDto;

    public PageableFacade(DataTableRequest request, DataTableResponse<DTO> response) {
        pageResponseDto = new PageResponseDto<>();
        pageResponseDto.setItems(response.getDtos());
        pageResponseDto.setCurrentPage(request.getCurrentPage());
        pageResponseDto.setPageSize(request.getPageSize());
        pageResponseDto.setSort(request.getSort());
        pageResponseDto.setOrder(request.getOrder());
        pageResponseDto.setItemsSize(response.getEntitiesSize());
        pageResponseDto.setTotalPageSize(response.getTotalPageSize());
        pageResponseDto.setCurrentShowFromEntries(response.getEntriesFrom());
        pageResponseDto.setCurrentShowToEntries(response.getEntriesTo());
        pageResponseDto.initPaginationState(request.getCurrentPage());
    }

    public PageResponseDto<DTO> getPageResponseDto() {
        return pageResponseDto;
    }

    public List<HeaderData> getHeaderDataList(List<HeaderName> headerNames) {
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : headerNames) {
            HeaderData headerData = new HeaderData();
            headerData.setHeaderName(headerName.columnName());
            headerData.setIsSortable(headerName.isSortable());
            headerData.setSort(headerName.dbName());
            if (pageResponseDto.getSort().equalsIgnoreCase(headerName.dbName())) {
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
}
