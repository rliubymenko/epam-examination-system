package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class AnswerServiceImpl implements AnswerService {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @PleaseInject
    private AnswerDao answerDao;

    @PleaseInject
    private QuestionDao questionDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private TransactionManager<Answer> transactionManager;

    @Override
    public boolean createAnswersForQuestion(List<AnswerDto> answers, QuestionDto questionDto) throws ServiceException {
        LOG.debug("Creating answers {} for question {}", answers, questionDto);
        transactionManager.begin(answerDao, questionDao, testDao);
        try {
            boolean isCreated = false;
            Optional<Question> question = questionDao.findByUuid(UUID.fromString(questionDto.getUuid()));
            if (question.isPresent()) {
                for (AnswerDto answer : answers) {
                    Answer currentAnswer = Answer.builder()
                            .setContent(answer.getContent())
                            .setIsCorrect(BooleanUtils.toBoolean(answer.getIsCorrect()))
                            .setQuestion(question.get())
                            .build();
                    answerDao.create(currentAnswer);
                }
                isCreated = true;
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
}
