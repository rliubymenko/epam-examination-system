package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserTestDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestDaoImplTest.class);

    @Mock
    private UserDao userDao;

    @Mock
    private TestDao testDao;

    @Mock
    private Connection connection;

    @Mock
    private Statement statement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private PreparedStatement preparedStatement;

    @Spy
    @InjectMocks
    private UserTestDaoImpl userTestDao;

    private long id;
    private UUID uuid;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private User expectedUser;
    private UserTest expectedUserTest;
    private List<UserTest> expectedUserTests;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", UserTestDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedUser = User.builder()
                .setUuid(uuid)
                .build();
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder()
                .setUuid(uuid)
                .build();
        expectedUserTest = UserTest.builder()
                .setUuid(uuid)
                .setIsSelected(true)
                .setIsCompleted(true)
                .setMarkValue(100F)
                .setAttemptNumber(1)
                .setStartTime(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setEndTime(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setUser(expectedUser)
                .setTest(expectedTest)
                .build();
        expectedUserTests = List.of(expectedUserTest);
        request = new DataTableRequest(1, 10, "created", "desc", "-1");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", UserTestDaoImplTest.class.getSimpleName());
    }

    @Test
    void shouldFindUserTestByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTest).when(userTestDao).extractEntity(resultSet);

        Optional<UserTest> actualUserTest = userTestDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userTestDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUserTest), actualUserTest);
    }

    @Test
    void shouldFindUserTestByUserAndTestUuid() throws DaoException, SQLException {
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTest).when(userTestDao).extractEntity(resultSet);

        Optional<UserTest> actualUserTest = userTestDao.findByUserAndTestUuid(uuid, uuid);

        ArgumentCaptor<UUID> userUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).findByUuid(userUuidCaptor.capture());
        Assertions.assertEquals(uuid, userUuidCaptor.getValue());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());

        Assertions.assertEquals(Optional.of(expectedUserTest), actualUserTest);
    }

    @Test
    void shouldFindUserTestByTestUuid() throws DaoException, SQLException {
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTests).when(userTestDao).extractEntities(resultSet);

        List<UserTest> actualUserTests = userTestDao.findByTestUuid(uuid);

        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());

        Assertions.assertEquals(expectedUserTests, actualUserTests);
    }

    @Test
    void shouldFindUserTestByUserUuid() throws DaoException, SQLException {
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTests).when(userTestDao).extractEntities(resultSet);

        List<UserTest> actualUserTest = userTestDao.findByUserUuid(uuid);

        ArgumentCaptor<UUID> userUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUuid(userUuidCaptor.capture());
        Assertions.assertEquals(uuid, userUuidCaptor.getValue());

        Assertions.assertEquals(expectedUserTests, actualUserTest);
    }

    @Test
    void shouldGetUserTestById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTest).when(userTestDao).extractEntity(resultSet);

        UserTest actualUserTest = userTestDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(userTestDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedUserTest, actualUserTest);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = userTestDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldCheckIfExistByUserUuid() throws DaoException, SQLException {
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = userTestDao.existsByUserUuid(uuid);

        ArgumentCaptor<UUID> userUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUuid(userUuidCaptor.capture());
        Assertions.assertEquals(uuid, userUuidCaptor.getValue());

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldCheckIfExistByTestUuid() throws DaoException, SQLException {
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = userTestDao.existsByTestUuid(uuid);

        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllUserTests() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTests).when(userTestDao).extractEntities(resultSet);

        List<UserTest> actualUsers = userTestDao.findAll();

        Assertions.assertEquals(expectedUserTests, actualUsers);
    }

    @Test
    void shouldFindAllUserTestsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUserTests).when(userTestDao).extractEntities(resultSet);

        List<UserTest> actualUsers = userTestDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(userTestDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedUserTests, actualUsers);
    }

    @Test
    void shouldCreateUserTest() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(userTestDao).populateInsertQuery(preparedStatement, expectedUserTest);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedUserTest).when(userTestDao).getById(id);

        UserTest actualUserTest = userTestDao.create(expectedUserTest);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<UserTest> userTestCaptor = ArgumentCaptor.forClass(UserTest.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(userTestDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).create(userTestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedUserTest, userTestCaptor.getValue());

        Assertions.assertEquals(expectedUserTest, actualUserTest);
    }

    @Test
    void shouldCreateAndSelectUserTest() throws DaoException, SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyLong(),
                    Mockito.anyLong(),
                    Mockito.anyBoolean()
            )).thenAnswer(invocation -> null);
            queryBuilderUtil.when(() -> QueryBuilderUtil.insertQuery(Mockito.anyString(), Mockito.anyInt())).thenReturn("INSERT INTO epam_user_test VALUES(default,default,?,?,?);");
            Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
            Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
            Mockito.when(resultSet.next()).thenReturn(true);
            Mockito.when(resultSet.getLong(1)).thenReturn(id);
            Mockito.doReturn(expectedUserTest).when(userTestDao).getById(id);

            UserTest actualUserTest = userTestDao.createAndSelect(expectedUserTest);

            ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
            ArgumentCaptor<UserTest> userTestCaptor = ArgumentCaptor.forClass(UserTest.class);
            ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

            Mockito.verify(userTestDao, Mockito.times(1)).getById(idCaptor.capture());
            Mockito.verify(userTestDao, Mockito.times(1)).createAndSelect(userTestCaptor.capture());
            Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
            Assertions.assertEquals(1, columIndexCaptor.getValue());
            Assertions.assertEquals(id, idCaptor.getValue());
            Assertions.assertEquals(expectedUserTest, userTestCaptor.getValue());

            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq(null),
                    Mockito.eq(null),
                    Mockito.eq(true)
            ));
            queryBuilderUtil.verify(() -> QueryBuilderUtil.insertQuery(Mockito.eq("epam_user_test"), Mockito.eq(3)));
            Assertions.assertEquals(expectedUserTest, actualUserTest);
        }
    }

    @Test
    void shouldUpdateUserTest() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(userTestDao).populateUpdateQuery(preparedStatement, expectedUserTest);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedUserTest)).when(userTestDao).findByUuid(uuid);

        UserTest actualUserTest = userTestDao.update(expectedUserTest);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UserTest> userTestCaptor = ArgumentCaptor.forClass(UserTest.class);

        Mockito.verify(userTestDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).update(userTestCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedUserTest, userTestCaptor.getValue());

        Assertions.assertEquals(expectedUserTest, actualUserTest);
    }

    @Test
    void shouldDeleteUserTestByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = userTestDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userTestDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldDeleteUserTestByTestUuid() throws DaoException, SQLException {
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = userTestDao.deleteByTestUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).deleteByTestUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldDeleteUserTestByUserUuid() throws DaoException, SQLException {
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = userTestDao.deleteByUserUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> userUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUuid(userUuidCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).deleteByUserUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(uuid, userUuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountUserTestsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = userTestDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(userTestDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldExtractUserTest() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractUserTest(resultSet, userDao, testDao)).thenReturn(expectedUserTest);
            UserTest actualUserTest = userTestDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedUserTest, actualUserTest);
        }
    }

    @Test
    void shouldExtractUserTests() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractUserTest(resultSet, userDao, testDao)).thenReturn(expectedUserTest);
            List<UserTest> actualUserTests = userTestDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedUserTests, actualUserTests);
        }
    }

    @Test
    void shouldReturnUserTestInsertQuery() {
        String expectedQuery = "INSERT INTO epam_user_test VALUES(default,default,?,?,?,?,?,?,?,?);";
        String actualQuery = userTestDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnUserTestUpdateQuery() {
        String expectedQuery = "UPDATE epam_user_test SET is_selected = ?, is_completed = ?, mark_value = ?, attempt_number = ?, start_time = ?, end_time = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = userTestDao.getUpdateQuery(expectedUserTest);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateUserTestInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyLong(),
                    Mockito.anyLong(),
                    Mockito.anyBoolean(),
                    Mockito.anyBoolean(),
                    Mockito.anyFloat(),
                    Mockito.anyInt(),
                    Mockito.any(LocalDateTime.class),
                    Mockito.any(LocalDateTime.class)
            )).thenAnswer(invocation -> null);
            userTestDao.populateInsertQuery(preparedStatement, expectedUserTest);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq(null),
                    Mockito.eq(null),
                    Mockito.eq(true),
                    Mockito.eq(true),
                    Mockito.eq(100F),
                    Mockito.eq(1),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30")),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30"))
            ));
        }
    }

    @Test
    void shouldPopulateUserTestUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyLong(),
                    Mockito.anyLong(),
                    Mockito.anyBoolean(),
                    Mockito.anyBoolean(),
                    Mockito.anyFloat(),
                    Mockito.anyInt(),
                    Mockito.any(LocalDateTime.class),
                    Mockito.any(LocalDateTime.class)
            )).thenAnswer(invocation -> null);
            userTestDao.populateUpdateQuery(preparedStatement, expectedUserTest);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq(true),
                    Mockito.eq(true),
                    Mockito.eq(100F),
                    Mockito.eq(1),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30")),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30"))
            ));
        }
    }
}
