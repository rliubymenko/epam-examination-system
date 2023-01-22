package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Subject;
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
import java.util.*;

@PleaseService
public class TestDaoImpl extends AbstractDao<Test> implements TestDao {

    private static final String ENTITY_NAME = "test";
    private static final String SUBJECT_ID = "subject_id";
    private static final Map<String, String> foreignTableNamesWithKeys = Map.of("subject", SUBJECT_ID);
    private static final Logger LOG = LoggerFactory.getLogger(TestDaoImpl.class);

    @PleaseInject
    private SubjectDao subjectDao;

    public TestDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.TEST_TABLE_NAME.getValue(), foreignTableNamesWithKeys);
    }

    @Override
    public boolean incrementTotalAttemptNumber(Test test) throws DaoException {
        LoggerUtil.updateEntityStartLogging(LOG, ENTITY_NAME, test.getUuid());
        List<String> columnNames = List.of("total_attempt_number");
        String updateQuery = QueryBuilderUtil.updateQueryByUuid(DaoConstant.TEST_TABLE_NAME.getValue(), test.getUuid(), columnNames);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, test.getTotalAttemptNumber() + 1);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.updateEntityErrorLogging(LOG, ENTITY_NAME, test.getUuid());
            throw new DaoException(message, e);
        }
    }

    @Override
    public Test getById(Long id) throws DaoException {
        Test test;
        LoggerUtil.findByIdStartLogging(LOG, ENTITY_NAME, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByIdQuery(DaoConstant.TEST_TABLE_NAME.getValue(), id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                test = extractTestWithoutSubject(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(LOG, ENTITY_NAME, id);
            throw new DaoException(message, e);
        }
        return test;
    }

    @Override
    public Optional<Test> findByUuidWithoutSubject(UUID uuid) throws DaoException {
        Optional<Test> maybeTest;
        LoggerUtil.findByUuidStartLogging(LOG, ENTITY_NAME, uuid);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByUuidQuery(DaoConstant.TEST_TABLE_NAME.getValue(), uuid);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                Test test = extractTestWithoutSubject(resultSet);
                maybeTest = Optional.ofNullable(test);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByUuidErrorLogging(LOG, ENTITY_NAME, uuid);
            throw new DaoException(message, e);
        }
        return maybeTest;
    }

    @Override
    public List<Test> findAllBySubjectUuid(UUID uuid) throws DaoException {
        Subject subject = getSubject(uuid);
        List<Test> tests;
        String subjectId = String.valueOf(subject.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, SUBJECT_ID, subjectId);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.findByQuery(DaoConstant.TEST_TABLE_NAME.getValue(), SUBJECT_ID, subjectId);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                tests = extractTestsWithoutSubject(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, SUBJECT_ID, subjectId);
            throw new DaoException(message, e);
        }
        return tests;
    }

    public Test extractTestWithoutSubject(ResultSet resultSet) throws SQLException {
        Test test = null;
        while (resultSet.next()) {
            test = DaoMapperUtil.extractTestWithoutSubject(resultSet);
        }
        return test;
    }

    public List<Test> extractTestsWithoutSubject(ResultSet resultSet) throws SQLException {
        List<Test> tests = new ArrayList<>();
        while (resultSet.next()) {
            Test test = DaoMapperUtil.extractTestWithoutSubject(resultSet);
            tests.add(test);
        }
        return tests;
    }

    @Override
    public Test extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Test test = null;
        while (resultSet.next()) {
            test = DaoMapperUtil.extractTest(resultSet, subjectDao);
        }
        return test;
    }

    @Override
    public List<Test> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Test> tests = new ArrayList<>();
        while (resultSet.next()) {
            Test test = DaoMapperUtil.extractTest(resultSet, subjectDao);
            tests.add(test);
        }
        return tests;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.insertQuery(DaoConstant.TEST_TABLE_NAME.getValue(), 8);
    }

    @Override
    public String getUpdateQuery(Test entity) {
        List<String> columnNames = List.of("name", "description", "complexity", "duration", "expiration_date", "max_attempt_number");
        return QueryBuilderUtil.updateQueryByUuid(DaoConstant.TEST_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Test entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getName(),
                entity.getDescription(),
                entity.getComplexity(),
                entity.getDuration(),
                entity.getTotalAttemptNumber(),
                entity.getSubject().getId(),
                entity.getExpirationDate(),
                entity.getMaxAttemptNumber()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Test entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getName(),
                entity.getDescription(),
                entity.getComplexity(),
                entity.getDuration(),
                entity.getExpirationDate(),
                entity.getMaxAttemptNumber()
        );
    }

    private Subject getSubject(UUID uuid) throws DaoException {
        Optional<Subject> maybeSubject = subjectDao.findByUuid(uuid);
        if (maybeSubject.isEmpty()) {
            String message = MessageFormat.format("Cannot find subject by uuid: {0}", uuid);
            LOG.error(message);
            throw new DaoException(message);
        }
        return maybeSubject.get();
    }
}
