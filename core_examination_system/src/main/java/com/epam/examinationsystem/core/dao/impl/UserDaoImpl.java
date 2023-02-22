package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.User;
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
import java.util.*;

@PleaseService
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String ENTITY_NAME = "user";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final Map<String, String> foreignTableNamesWithKeys = Map.of("role", "role_id");
    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @PleaseInject
    private RoleDao roleDao;

    public UserDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.USER_TABLE_NAME.getValue(), foreignTableNamesWithKeys);
    }

    @Override
    public Optional<User> findByUsername(String username) throws DaoException {
        Optional<User> maybeUser;
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, USERNAME, username);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByWrappedValueQuery(DaoConstant.USER_TABLE_NAME.getValue(), USERNAME, username);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                User user = extractEntity(resultSet);
                maybeUser = Optional.ofNullable(user);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, USERNAME, username);
            throw new DaoException(message, e);
        }
        return maybeUser;
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> maybeUser;
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, EMAIL, email);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByWrappedValueQuery(DaoConstant.USER_TABLE_NAME.getValue(), EMAIL, email);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                User user = extractEntity(resultSet);
                maybeUser = Optional.ofNullable(user);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, EMAIL, email);
            throw new DaoException(message, e);
        }
        return maybeUser;
    }

    @Override
    public boolean resetPassword(UUID userUuid, String newEncryptedPassword) throws DaoException {
        LoggerUtil.updateEntityStartLogging(LOG, ENTITY_NAME, userUuid);
        List<String> columnNames = List.of("password");
        String updateQuery = QueryBuilderUtil.updateQueryByUuid(DaoConstant.USER_TABLE_NAME.getValue(), userUuid, columnNames);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newEncryptedPassword);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.updateEntityErrorLogging(LOG, ENTITY_NAME, userUuid);
            throw new DaoException(message, e);
        }
    }

    @Override
    public boolean updateWithoutChangingActivation(User user) throws DaoException {
        LoggerUtil.updateEntityStartLogging(LOG, ENTITY_NAME, user.getUuid());
        String updateQuery = getUpdateWithoutChangingActivationQuery(user);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            populateUpdateWithoutChangingActivationQuery(preparedStatement, user);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.updateEntityErrorLogging(LOG, ENTITY_NAME, user.getUuid());
            throw new DaoException(message, e);
        }
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
        return QueryBuilderUtil.insertQuery(DaoConstant.USER_TABLE_NAME.getValue(), 7);
    }

    @Override
    public String getUpdateQuery(User user) {
        List<String> columnNames = List.of("username", "email", "first_name", "last_name", "is_activated");
        return QueryBuilderUtil.updateQueryByUuid(DaoConstant.USER_TABLE_NAME.getValue(), user.getUuid(), columnNames);
    }

    private String getUpdateWithoutChangingActivationQuery(User user) {
        List<String> columnNames = List.of("username", "email", "first_name", "last_name");
        return QueryBuilderUtil.updateQueryByUuid(DaoConstant.USER_TABLE_NAME.getValue(), user.getUuid(), columnNames);
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
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsActivated()
        );
    }

    public void populateUpdateWithoutChangingActivationQuery(PreparedStatement preparedStatement, User user) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }
}
