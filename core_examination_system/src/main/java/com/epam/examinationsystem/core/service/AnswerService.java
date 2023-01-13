package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;

public interface AnswerService {

    boolean createAnswersForQuestion(List<AnswerDto> answers, QuestionDto questionDto) throws ServiceException;

    DataTableResponse<AnswerDto> findAll(DataTableRequest request) throws ServiceException;
}
