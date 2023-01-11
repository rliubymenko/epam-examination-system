package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
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
import java.util.ArrayList;
import java.util.List;

@PleaseService
public class TestDaoImpl extends AbstractDao<Test> implements TestDao {

    private static final String ENTITY_NAME = "test";
    private static final Logger LOG = LoggerFactory.getLogger(TestDaoImpl.class);

    @PleaseInject
    private SubjectDao subjectDao;

    public TestDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.TEST_TABLE_NAME.getValue());
    }

    @Override
    public Test getById(Long id) throws DaoException {
        Test test;
        LoggerUtil.findByIdStartLogging(LOG, ENTITY_NAME, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByIdQuery(DaoConstant.TEST_TABLE_NAME.getValue(), id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                test = extractTestWithoutSubject(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(LOG, ENTITY_NAME, id);
            throw new DaoException(message, e);
        }
        return test;
    }

    public Test extractTestWithoutSubject(ResultSet resultSet) throws SQLException {
        Test test = null;
        while (resultSet.next()) {
            test = DaoMapperUtil.extractTestWithoutSubject(resultSet);
        }
        return test;
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
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.TEST_TABLE_NAME.getValue(), 9);
    }

    @Override
    public String getUpdateQuery(Test entity) {
        List<String> columnNames = List.of("name", "description", "complexity", "duration", "expiration_date", "max_attempt_number");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.TEST_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
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
                entity.getCreationDate(),
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
}
