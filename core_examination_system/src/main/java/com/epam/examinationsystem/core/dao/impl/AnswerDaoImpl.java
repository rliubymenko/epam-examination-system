package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.*;

@PleaseService
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final String ENTITY_NAME = "answer";
    private static final String QUESTION_ID = "question_id";
    private static final Map<String, String> foreignTableNamesWithKeys = Map.of("question", QUESTION_ID);
    private static final Logger LOG = LoggerFactory.getLogger(AnswerDaoImpl.class);

    @PleaseInject
    private QuestionDao questionDao;

    public AnswerDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.ANSWER_TABLE_NAME.getValue(), foreignTableNamesWithKeys);
    }

    @Override
    public List<Answer> findAllByQuestionUuid(UUID uuid) throws DaoException {
        Question question = getQuestion(uuid);
        List<Answer> answers;
        String questionId = String.valueOf(question.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, QUESTION_ID, questionId);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.findByQuery(DaoConstant.ANSWER_TABLE_NAME.getValue(), QUESTION_ID, questionId);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                answers = extractEntities(resultSet);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, QUESTION_ID, questionId);
            throw new DaoException(message, e);
        }
        return answers;
    }

    @Override
    public Answer extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Answer answer = null;
        while (resultSet.next()) {
            answer = DaoMapperUtil.extractAnswer(resultSet, questionDao);
        }
        return answer;
    }

    @Override
    public List<Answer> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Answer> answers = new ArrayList<>();
        while (resultSet.next()) {
            Answer answer = DaoMapperUtil.extractAnswer(resultSet, questionDao);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.insertQuery(DaoConstant.ANSWER_TABLE_NAME.getValue(), 3);
    }

    @Override
    public String getUpdateQuery(Answer entity) {
        List<String> columnNames = List.of("content", "is_correct");
        return QueryBuilderUtil.updateQueryByUuid(DaoConstant.ANSWER_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Answer entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getContent(),
                entity.getIsCorrect(),
                entity.getQuestion().getId()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Answer entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getContent(),
                entity.getIsCorrect()
        );
    }

    private Question getQuestion(UUID uuid) throws DaoException {
        Optional<Question> maybeQuestion = questionDao.findByUuid(uuid);
        if (maybeQuestion.isEmpty()) {
            String message = MessageFormat.format("Cannot find question by uuid: {0}", uuid);
            LOG.error(message);
            throw new DaoException(message);
        }
        return maybeQuestion.get();
    }
}
