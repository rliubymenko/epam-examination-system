package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    QuestionDto create(QuestionDto questionDto) throws ServiceException;

    boolean update(QuestionDto questionDto) throws ServiceException;

    Optional<QuestionDto> findByUuid(UUID uuid) throws ServiceException;

    boolean existsByUuid(UUID uuid) throws ServiceException;

    DataTableResponse<QuestionDto> findAll(DataTableRequest request) throws ServiceException;

    List<QuestionDto> findAllOpenToCreateAnswers() throws ServiceException;

    boolean deleteByUuid(UUID uuid) throws ServiceException;
}
