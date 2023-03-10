package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestService {

    boolean create(TestDto testDto) throws ServiceException;

    boolean update(TestDto testDto) throws ServiceException;

    Optional<TestDto> findByUuid(UUID uuid) throws ServiceException;

    List<Test> findAllBySubjectUuidForStudent(UUID uuid) throws ServiceException;

    Optional<StudentTestDto> findByUuidForTesting(UUID uuid) throws ServiceException;

    DataTableResponse<TestDto> findAll(DataTableRequest request) throws ServiceException;

    DataTableResponse<TestDto> findAllForStudent(DataTableRequest request, UUID currentUserUuid) throws ServiceException;

    List<TestDto> findAll() throws ServiceException;

    boolean existsByUuid(UUID uuid) throws ServiceException;

    boolean deleteByUuid(UUID uuid) throws ServiceException;
}
