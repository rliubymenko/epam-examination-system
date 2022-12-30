package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.User;
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
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String ENTITY_NAME = "user";
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @PleaseInject
    private RoleDao roleDao;

    public UserDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.USER_TABLE_NAME.getValue());
    }

    @Override
    public User getById(Long id) throws DaoException {
        User user;
        LoggerUtil.findByIdStartLogging(LOG, ENTITY_NAME, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByIdQuery(DaoConstant.USER_TABLE_NAME.getValue(), id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                user = extractEntity(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(LOG, ENTITY_NAME, id);
            throw new DaoException(message, e);
        }
        return user;
    }

    @Override
    public User extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        User user = null;
        while (resultSet.next()) {
            user = DaoMapperUtil.extractUser(resultSet, roleDao);
        }
        return user;
    }

    @Override
    public List<User> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User entity = DaoMapperUtil.extractUser(resultSet, roleDao);
            users.add(entity);
        }
        return users;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.USER_TABLE_NAME.getValue(), 7);
    }

    @Override
    public String getUpdateQuery(User user) {
        List<String> columnNames = List.of("username", "password", "email", "first_name", "last_name", "is_activated");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.USER_TABLE_NAME.getValue(), user.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, User user) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsActivated(),
                user.getRole().getId()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, User user) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsActivated()
        );
    }
}
