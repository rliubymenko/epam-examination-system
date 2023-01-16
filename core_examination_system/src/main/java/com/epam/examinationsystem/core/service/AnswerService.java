package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerService {

    Optional<AnswerDto> findByUuid(UUID uuid) throws ServiceException;

    List<AnswerDto> findAllByQuestionUuid(UUID uuid) throws ServiceException;

    void createAnswersForQuestion(List<AnswerDto> answers, QuestionDto questionDto) throws ServiceException;

    boolean update(AnswerDto answerDto) throws ServiceException;

    boolean updateAnswerAndSetNewTrueAnswer(AnswerDto answerDto, UUID newTrueAnswerUuid) throws ServiceException;

    DataTableResponse<AnswerDto> findAll(DataTableRequest request) throws ServiceException;

    boolean deleteByUuidAndQuestionUuid(UUID answerUuid, UUID questionUuid) throws ServiceException;

    boolean deleteByUuidAndSetNewTrueAnswer(UUID answerUuid, UUID newTrueAnswerUuid) throws ServiceException;

    boolean existsByUuid(UUID uuid) throws ServiceException;
}
