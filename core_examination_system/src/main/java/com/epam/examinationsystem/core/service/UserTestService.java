package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;
import java.util.UUID;

public interface UserTestService {

    boolean create(UserTestDto userTestDto) throws ServiceException;

    boolean createAfterExam(UserTestDto userTestDto) throws ServiceException;

    List<UserTestDto> findByUserUuid(UUID uuid) throws ServiceException;

    int getCurrentAttemptNumber(UUID userUuid, UUID testUuid) throws ServiceException;

    boolean isSelected(UUID userUuid, UUID testUuid) throws ServiceException;

    DataTableResponse<UserTestDto> findAll(DataTableRequest request) throws ServiceException;

    List<UserTestDto> findAll() throws ServiceException;
}
