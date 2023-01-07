package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.LoggerUtil;
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
public class UserTestDaoImpl extends AbstractDao<UserTest> implements UserTestDao {

    private static final String ENTITY_NAME = "user_test";
    private static final String USER_ID = "epam_user_id";
    private static final String TEST_ID = "test_id";
    private static final Logger LOG = LoggerFactory.getLogger(UserTestDaoImpl.class);

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private TestDao testDao;

    public UserTestDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.USER_TEST_TABLE_NAME.getValue());
    }

    @Override
    public Optional<UserTest> findByUserUuid(UUID uuid) throws DaoException {
        User user = getUser(uuid);
        Optional<UserTest> maybeUserTest;
        String userId = String.valueOf(user.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, USER_ID, userId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId, false);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                UserTest entity = extractEntity(resultSet);
                maybeUserTest = Optional.ofNullable(entity);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, USER_ID, userId);
            throw new DaoException(message, e);
        }
        return maybeUserTest;
    }

    @Override
    public Optional<UserTest> findByTestUuid(UUID uuid) throws DaoException {
        Test test = getTest(uuid);
        Optional<UserTest> maybeUserTest;
        String testId = String.valueOf(test.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, TEST_ID, testId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId, false);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                UserTest entity = extractEntity(resultSet);
                maybeUserTest = Optional.ofNullable(entity);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, TEST_ID, testId);
            throw new DaoException(message, e);
        }
        return maybeUserTest;
    }

    @Override
    public boolean existsByUserUuid(UUID uuid) throws DaoException {
        long count = 0;
        User user = getUser(uuid);
        String userId = String.valueOf(user.getId());
        LoggerUtil.existByStartLogging(LOG, ENTITY_NAME, USER_ID, userId);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.generateCountByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    count = resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.existByErrorLogging(LOG, ENTITY_NAME, USER_ID, userId);
            throw new DaoException(message, e);
        }
        return count == 1;
    }

    @Override
    public boolean existsByTestUuid(UUID uuid) throws DaoException {
        long count = 0;
        Test test = getTest(uuid);
        String testId = String.valueOf(test.getId());
        LoggerUtil.existByStartLogging(LOG, ENTITY_NAME, TEST_ID, testId);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.generateCountByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    count = resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.existByErrorLogging(LOG, ENTITY_NAME, TEST_ID, testId);
            throw new DaoException(message, e);
        }
        return count == 1;
    }

    @Override
    public boolean deleteByUserUuid(UUID uuid) throws DaoException {
        User user = getUser(uuid);
        String userId = String.valueOf(user.getId());
        LoggerUtil.deleteByStartLogging(LOG, ENTITY_NAME, USER_ID, userId);
        String deleteQuery = QueryBuilderUtil.generateDeleteQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.deleteByErrorLogging(LOG, ENTITY_NAME, USER_ID, userId);
            throw new DaoException(message, e);
        }
    }

    @Override
    public boolean deleteByTestUuid(UUID uuid) throws DaoException {
        Test test = getTest(uuid);
        String testId = String.valueOf(test.getId());
        LoggerUtil.deleteByStartLogging(LOG, ENTITY_NAME, TEST_ID, testId);
        String deleteQuery = QueryBuilderUtil.generateDeleteQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.deleteByErrorLogging(LOG, ENTITY_NAME, TEST_ID, testId);
            throw new DaoException(message, e);
        }
    }

    @Override
    public UserTest extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        UserTest userTest = null;
        while (resultSet.next()) {
            userTest = DaoMapperUtil.extractUserTest(resultSet, userDao, testDao);
        }
        return userTest;
    }

    @Override
    public List<UserTest> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<UserTest> userTests = new ArrayList<>();
        while (resultSet.next()) {
            UserTest userTest = DaoMapperUtil.extractUserTest(resultSet, userDao, testDao);
            userTests.add(userTest);
        }
        return userTests;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), 8);
    }

    @Override
    public String getUpdateQuery(UserTest entity) {
        List<String> columnNames = List.of("is_selected", "is_completed", "mark_value", "attempt_number", "start_time", "end_time");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.USER_TEST_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, UserTest entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getUser().getId(),
                entity.getTest().getId(),
                entity.getIsSelected(),
                entity.getIsCompleted(),
                entity.getMarkValue(),
                entity.getAttemptNumber(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, UserTest entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getIsSelected(),
                entity.getIsCompleted(),
                entity.getMarkValue(),
                entity.getAttemptNumber(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    private User getUser(UUID uuid) throws DaoException {
        Optional<User> maybeUser = userDao.findByUuid(uuid);
        if (maybeUser.isEmpty()) {
            String message = MessageFormat.format("Cannot find user by uuid: {0}", uuid);
            LOG.error(message);
            throw new DaoException(message);
        }
        return maybeUser.get();
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
