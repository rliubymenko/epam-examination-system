package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    boolean create(QuestionDto questionDto) throws ServiceException;

    Optional<QuestionDto> findByTestUuid(UUID uuid) throws ServiceException;
}
