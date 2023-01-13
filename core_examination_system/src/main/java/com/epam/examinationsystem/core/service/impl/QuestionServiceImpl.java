package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @PleaseInject
    private QuestionDao questionDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private TransactionManager<Question> transactionManager;

    @Override
    public boolean create(QuestionDto questionDto) throws ServiceException {
        LOG.debug("Creating question by dto {}", questionDto);
        transactionManager.begin(questionDao, testDao, subjectDao);
        try {
            boolean isCreated = false;
            Optional<Test> maybeTest = testDao.findByUuid(UUID.fromString(questionDto.getTest().getUuid()));
            if (maybeTest.isPresent()) {
                Question question = Question.builder()
                        .setContent(questionDto.getContent())
                        .setDescription(questionDto.getDescription())
                        .setType(EnumUtils.getEnumIgnoreCase(QuestionType.class, questionDto.getType()))
                        .setTest(maybeTest.get())
                        .build();
                isCreated = questionDao.create(question);
            }
            transactionManager.commit();
            return isCreated;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public Optional<QuestionDto> findByTestUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find question by test uuid {}", uuid);
        if (uuid == null) {
            return Optional.empty();
        }
        transactionManager.begin(questionDao, testDao, subjectDao);
        try {
            Optional<Question> maybeQuestion = questionDao.findByTestUuid(uuid);
            return maybeQuestion.map(QuestionDto.builder()::fromQuestion);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public DataTableResponse<QuestionDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all questions by {}", request);
        transactionManager.beginWithAutoCommit(questionDao, testDao, subjectDao);
        try {
            List<Question> questions = questionDao.findAll(request);
            DataTableResponse<QuestionDto> response = PageableUtil.calculatePageableData(request, testDao);
            List<QuestionDto> questionDtos = questions.stream()
                    .map(QuestionDto.builder()::fromQuestion)
                    .toList();
            response.setDtos(questionDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }
}
