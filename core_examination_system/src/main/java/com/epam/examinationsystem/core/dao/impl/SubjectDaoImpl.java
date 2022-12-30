package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.DaoMapperUtil;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.QueryBuilderUtil;
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
    private static final Logger LOG = LoggerFactory.getLogger(SubjectDaoImpl.class);

    @PleaseInject
    private UserDao userDao;

    public SubjectDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.SUBJECT_TABLE_NAME.getValue());
    }

    @Override
    public Subject getById(Long id) throws DaoException {
        Subject subject;
        LoggerUtil.findByIdStartLogging(LOG, ENTITY_NAME, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByIdQuery(DaoConstant.SUBJECT_TABLE_NAME.getValue(), id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                subject = extractEntity(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(LOG, ENTITY_NAME, id);
            throw new DaoException(message, e);
        }
        return subject;
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
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.SUBJECT_TABLE_NAME.getValue(), 3);
    }

    @Override
    public String getUpdateQuery(Subject entity) {
        List<String> columnNames = List.of("name", "description", "epam_user_id");
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
                entity.getDescription(),
                entity.getUser().getId()
        );
    }
}
