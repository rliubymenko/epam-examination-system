package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.entity.Test;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private static final String ENTITY_NAME = "question";
    private static final String TEST_ID = "test_id";

    private static final Logger LOG = LoggerFactory.getLogger(QuestionDaoImpl.class);

    @PleaseInject
    private TestDao testDao;

    public QuestionDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.QUESTION_TABLE_NAME.getValue());
    }

    @Override
    public Optional<Question> findByTestUuid(UUID uuid) throws DaoException {
        Test test = getTest(uuid);
        Optional<Question> maybeQuestion;
        String testId = String.valueOf(test.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, TEST_ID, testId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByQuery(DaoConstant.QUESTION_TABLE_NAME.getValue(), TEST_ID, testId);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                Question entity = extractEntity(resultSet);
                maybeQuestion = Optional.ofNullable(entity);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, TEST_ID, testId);
            throw new DaoException(message, e);
        }
        return maybeQuestion;
    }

    @Override
    public Question extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Question question = null;
        while (resultSet.next()) {
            question = DaoMapperUtil.extractQuestion(resultSet, testDao);
        }
        return question;
    }

    @Override
    public List<Question> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
            Question question = DaoMapperUtil.extractQuestion(resultSet, testDao);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.QUESTION_TABLE_NAME.getValue(), 4);
    }

    @Override
    public String getUpdateQuery(Question entity) {
        List<String> columnNames = List.of("type", "content", "description", "test_id");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.QUESTION_TABLE_NAME.getValue(), entity.getUuid(), columnNames);

    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Question entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getType(),
                entity.getContent(),
                entity.getDescription(),
                entity.getTest().getId()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Question entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getType(),
                entity.getContent(),
                entity.getDescription()
        );
    }

    private Test getTest(UUID uuid) throws DaoException {
        Optional<Test> maybeTest = testDao.findByUuid(uuid);
        if (maybeTest.isEmpty()) {
            String message = MessageFormat.format("Cannot find test by uuid: {0}", uuid);
            LOG.error(message);
            throw new DaoException(message);
        }
        return maybeTest.get();
    }
}
