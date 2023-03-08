package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.*;

/**
 * The dao implementation for Test entity.
 */
@PleaseService
public class TestDaoImpl extends AbstractDao<Test> implements TestDao {

    private static final String ENTITY_NAME = "test";
    private static final String WHERE_CLAUSE = "where";
    private static final String SUBJECT_ID = "subject_id";
    private static final Map<String, String> foreignTableNamesWithKeys = Map.of("subject", SUBJECT_ID);
    private static final Logger LOG = LoggerFactory.getLogger(TestDaoImpl.class);
    private static final String FIND_ALL_READY_TO_PASS_TESTS_BY_SUBJECT_UUID = """
            SELECT *
            FROM test
                     LEFT JOIN subject ON test.subject_id = subject.id
            WHERE test.id NOT IN (SELECT DISTINCT test.id
                                  FROM test
                                           LEFT JOIN question ON question.test_id = test.id
                                           LEFT JOIN answer ON answer.question_id = question.id
                                  where answer.id IS NULL OR question.id IS NULL)
                AND subject.id = """;
    private static final String FIND_ALL_READY_TO_PASS_TESTS = """
            SELECT *
            FROM test
            WHERE test.id NOT IN (SELECT DISTINCT test.id
                                  FROM test
                                           LEFT JOIN question ON question.test_id = test.id
                                           LEFT JOIN answer ON answer.question_id = question.id
                                  where answer.id IS NULL OR question.id IS NULL);
                                  """;
    private static final String FIND_ALL_READY_TO_PASS_QUERY_PART = """
            test.id NOT IN (SELECT DISTINCT test.id
                                  FROM test
                                           LEFT JOIN question ON question.test_id = test.id
                                           LEFT JOIN answer ON answer.question_id = question.id
                                  where answer.id IS NULL OR question.id IS NULL)
            """;


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
    public List<Test> findAllForStudent(DataTableRequest request) throws DaoException {
        List<Test> entities;
        LoggerUtil.findAllByParametersStartLogging(LOG, ENTITY_NAME, request);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.findAllAndJoinTableByForeignKeyByQueryParameters(DaoConstant.TEST_TABLE_NAME.getValue(), request, foreignTableNamesWithKeys);
            findAllQuery = addReadyToTestFilter(findAllQuery);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllByParametersErrorLogging(LOG, ENTITY_NAME, request);
            throw new DaoException(message, e);
        }
        return entities;
    }

    @Override
    public List<Test> findAllForStudent() throws DaoException {
        List<Test> entities;
        LoggerUtil.findAllStartLogging(LOG, ENTITY_NAME);
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(FIND_ALL_READY_TO_PASS_TESTS)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllErrorLogging(LOG, ENTITY_NAME);
            throw new DaoException(message, e);
        }
        return entities;
    }

    @Override
    public long countForStudent(DataTableRequest request) throws DaoException {
        LoggerUtil.countingRecordsStartLogging(LOG, ENTITY_NAME);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.countQuery(DaoConstant.TEST_TABLE_NAME.getValue(), request, foreignTableNamesWithKeys);
            countQuery = addReadyToTestFilter(countQuery);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    return resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.countingRecordsErrorLogging(LOG, ENTITY_NAME);
            throw new DaoException(message, e);
        }
        return 0;
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
            String findAllQuery = FIND_ALL_READY_TO_PASS_TESTS_BY_SUBJECT_UUID + subjectId + ";";
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

    private String addReadyToTestFilter(String query) {
        if (StringUtils.containsIgnoreCase(query, WHERE_CLAUSE)) {
            int whereIndex = StringUtils.indexOfIgnoreCase(query, WHERE_CLAUSE) + WHERE_CLAUSE.length();
            return query.substring(0, whereIndex) +
                    " " +
                    FIND_ALL_READY_TO_PASS_QUERY_PART +
                    " AND " +
                    query.substring(whereIndex);
        }
        int whereIndex = StringUtils.indexOfIgnoreCase(query, "subject.id") + "subject.id".length();
        return query.substring(0, whereIndex) +
                " WHERE " +
                FIND_ALL_READY_TO_PASS_QUERY_PART +
                " " +
                query.substring(whereIndex);
    }
}
