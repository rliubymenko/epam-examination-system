package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Subject;
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
public class SubjectDaoImpl extends AbstractDao<Subject> implements SubjectDao {

    private static final String ENTITY_NAME = "subject";
    private static final String NAME = "name";
    private static final Logger LOG = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @PleaseInject
    private UserDao userDao;

    public SubjectDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.SUBJECT_TABLE_NAME.getValue());
    }

    @Override
    public boolean existsByName(String name) throws DaoException {
        long count = 0;
        LoggerUtil.existByStartLogging(LOG, ENTITY_NAME, NAME, name);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.generateCountByWrappedValueQuery(DaoConstant.SUBJECT_TABLE_NAME.getValue(), NAME, name);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    count = resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.existByErrorLogging(LOG, ENTITY_NAME, NAME, name);
            throw new DaoException(message, e);
        }
        return count == 1;
    }

    @Override
    public Subject extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Subject subject = null;
        while (resultSet.next()) {
            subject = DaoMapperUtil.extractSubject(resultSet, userDao);
        }
        return subject;
    }

    @Override
    public List<Subject> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Subject> subjects = new ArrayList<>();
        while (resultSet.next()) {
            Subject subject = DaoMapperUtil.extractSubject(resultSet, userDao);
            subjects.add(subject);
        }
        return subjects;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.SUBJECT_TABLE_NAME.getValue(), 2);
    }

    @Override
    public String getUpdateQuery(Subject entity) {
        List<String> columnNames = List.of("name", "description");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.SUBJECT_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Subject entity) throws SQLException {
        populatePreparedStatement(preparedStatement, entity);
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Subject entity) throws SQLException {
        populatePreparedStatement(preparedStatement, entity);
    }

    private void populatePreparedStatement(PreparedStatement preparedStatement, Subject entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getName(),
                entity.getDescription()
        );
    }
}
