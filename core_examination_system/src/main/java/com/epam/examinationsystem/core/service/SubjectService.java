package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.Optional;
import java.util.UUID;

public interface SubjectService {

    boolean create(SubjectDto subjectDto) throws ServiceException;

    boolean update(SubjectDto subjectDto) throws ServiceException;

    Optional<SubjectDto> findByUuid(UUID uuid) throws ServiceException;

    DataTableResponse<SubjectDto> findAll(DataTableRequest request) throws ServiceException;

    boolean existsByUuid(UUID uuid) throws ServiceException;

    boolean existsByName(String name) throws ServiceException;
}
