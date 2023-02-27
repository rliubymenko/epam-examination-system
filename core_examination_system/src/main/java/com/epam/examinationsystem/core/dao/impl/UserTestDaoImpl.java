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
import java.time.LocalDateTime;
import java.util.*;

@PleaseService
public class UserTestDaoImpl extends AbstractDao<UserTest> implements UserTestDao {

    private static final String ENTITY_NAME = "user_test";
    private static final String USER_ID = "epam_user_id";
    private static final String TEST_ID = "test_id";
    private static final Map<String, String> foreignTableNamesWithKeys = Map.of(
            "epam_user", USER_ID,
            "test", TEST_ID
    );
    private static final Logger LOG = LoggerFactory.getLogger(UserTestDaoImpl.class);

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private TestDao testDao;

    public UserTestDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.USER_TEST_TABLE_NAME.getValue(), foreignTableNamesWithKeys);
    }

    @Override
    public UserTest createAndSelect(UserTest userTest) throws DaoException {
        LoggerUtil.createEntityStartLogging(LOG, ENTITY_NAME);
        String insertQuery = QueryBuilderUtil.insertQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), 3);
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            QueryBuilderUtil.populatePreparedStatement(
                    preparedStatement,
                    userTest.getUser().getId(),
                    userTest.getTest().getId(),
                    userTest.getIsSelected()
            );
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long lastGeneratedId = resultSet.getLong(1);
            return getById(lastGeneratedId);
        } catch (SQLException e) {
            String message = LoggerUtil.createEntityErrorLogging(LOG, ENTITY_NAME);
            throw new DaoException(message, e);
        }
    }

    @Override
    public void setStartTime(UUID uuid, LocalDateTime startTime) throws DaoException {
        LoggerUtil.updateEntityStartLogging(LOG, ENTITY_NAME, uuid);
        List<String> columnNames = List.of("start_time");
        String updateQuery = QueryBuilderUtil.updateQueryByUuid(DaoConstant.USER_TEST_TABLE_NAME.getValue(), uuid, columnNames);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            QueryBuilderUtil.populatePreparedStatement(preparedStatement, startTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = LoggerUtil.updateEntityErrorLogging(LOG, ENTITY_NAME, uuid);
            throw new DaoException(message, e);
        }
    }

    @Override
    public List<UserTest> findByUserUuid(UUID uuid) throws DaoException {
        User user = getUser(uuid);
        List<UserTest> userTests;
        String userId = String.valueOf(user.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, USER_ID, userId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                userTests = extractEntities(resultSet);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, USER_ID, userId);
            throw new DaoException(message, e);
        }
        return userTests;
    }

    @Override
    public List<UserTest> findByTestUuid(UUID uuid) throws DaoException {
        Test test = getTest(uuid);
        List<UserTest> userTests;
        String testId = String.valueOf(test.getId());
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, TEST_ID, testId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                userTests = extractEntities(resultSet);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, TEST_ID, testId);
            throw new DaoException(message, e);
        }
        return userTests;
    }

    @Override
    public Optional<UserTest> findByUserAndTestUuid(UUID userUuid, UUID testUuid) throws DaoException {
        User user = getUser(userUuid);
        Test test = getTest(testUuid);
        Optional<UserTest> maybeUserTest;
        String userId = String.valueOf(user.getId());
        String testId = String.valueOf(test.getId());
        Map<String, String> columnWithPredicates = Map.of(USER_ID, userId, TEST_ID, testId);
        LoggerUtil.findByStartLogging(LOG, ENTITY_NAME, USER_ID + " and " + TEST_ID, userId + " and " + testId);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findAllByPredicates(DaoConstant.USER_TEST_TABLE_NAME.getValue(), columnWithPredicates);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                UserTest entity = extractEntity(resultSet);
                maybeUserTest = Optional.ofNullable(entity);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByErrorLogging(LOG, ENTITY_NAME, USER_ID + " and " + TEST_ID, userId + " and " + testId);
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
            String countQuery = QueryBuilderUtil.countByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId);
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
            String countQuery = QueryBuilderUtil.countByQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId);
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
        String deleteQuery = QueryBuilderUtil.deleteQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), USER_ID, userId);
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
        String deleteQuery = QueryBuilderUtil.deleteQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), TEST_ID, testId);
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
        return QueryBuilderUtil.insertQuery(DaoConstant.USER_TEST_TABLE_NAME.getValue(), 8);
    }

    @Override
    public String getUpdateQuery(UserTest entity) {
        List<String> columnNames = List.of("is_selected", "is_completed", "mark_value", "attempt_number", "start_time", "end_time");
        return QueryBuilderUtil.updateQueryByUuid(DaoConstant.USER_TEST_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
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
