package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.enumeration.UserType;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImplTest.class);

    @Mock
    private RoleDao roleDao;

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
    private UserDaoImpl userDao;

    private long id;
    private UUID uuid;
    private Role expectedRole;
    private User expectedUser;
    private List<User> expectedUsers;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", UserDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedRole = Role.builder()
                .setUuid(uuid)
                .setName(UserType.ADMIN)
                .build();
        expectedUser = User.builder()
                .setUuid(uuid)
                .setUsername("username")
                .setEmail("email@dsf.ds")
                .setPassword("dsfdsfgsdgfdfghfdgh")
                .setFirstName("firstname")
                .setLastName("lastname")
                .setIsActivated(true)
                .setRole(expectedRole)
                .build();
        expectedUsers = List.of(expectedUser);
        request = new DataTableRequest(1, 10, "created", "desc", "-1");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", UserDaoImplTest.class.getSimpleName());
    }

    @Test
    void shouldFindUserByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUser).when(userDao).extractEntity(resultSet);

        Optional<User> actualUser = userDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    void shouldFindUserByUsername() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUser).when(userDao).extractEntity(resultSet);

        Optional<User> actualUser = userDao.findByUsername("username");

        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUsername(usernameCaptor.capture());
        Assertions.assertEquals("username", usernameCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUser), actualUser);
    }


    @Test
    void shouldFindUserByEmail() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUser).when(userDao).extractEntity(resultSet);

        Optional<User> actualUser = userDao.findByEmail("email@dsf.ds");

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(userDao, Mockito.times(1)).findByEmail(emailCaptor.capture());
        Assertions.assertEquals("email@dsf.ds", emailCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUser), actualUser);
    }

    @Test
    void shouldGetUserById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUser).when(userDao).extractEntity(resultSet);

        User actualUser = userDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(userDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = userDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllUsers() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUsers).when(userDao).extractEntities(resultSet);

        List<User> actualUsers = userDao.findAll();

        Assertions.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void shouldFindAllUsersByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedUsers).when(userDao).extractEntities(resultSet);

        List<User> actualUsers = userDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(userDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void shouldCreateUser() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(userDao).populateInsertQuery(preparedStatement, expectedUser);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedUser).when(userDao).getById(id);

        User actualUser = userDao.create(expectedUser);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(userDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).create(userCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedUser, userCaptor.getValue());

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldUpdateUser() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(userDao).populateUpdateQuery(preparedStatement, expectedUser);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedUser)).when(userDao).findByUuid(uuid);

        User actualUser = userDao.update(expectedUser);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(userDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).update(userCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedUser, userCaptor.getValue());

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void shouldUpdateUserWithoutPassword() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(userDao).populateUpdateWithoutPasswordQuery(preparedStatement, expectedUser);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualResult = userDao.updateWithoutPassword(expectedUser);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        Mockito.verify(userDao, Mockito.times(1)).updateWithoutPassword(userCaptor.capture());
        Assertions.assertEquals(expectedUser, userCaptor.getValue());

        Assertions.assertTrue(actualResult);
    }

    @Test
    void shouldDeleteUserByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = userDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(userDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountUsersByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = userDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(userDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldExtractUser() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractUser(resultSet, roleDao)).thenReturn(expectedUser);
            User actualUser = userDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedUser, actualUser);
        }
    }

    @Test
    void shouldExtractUsers() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractUser(resultSet, roleDao)).thenReturn(expectedUser);
            List<User> actualUsers = userDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedUsers, actualUsers);
        }
    }

    @Test
    void shouldReturnUserInsertQuery() {
        String expectedQuery = "INSERT INTO epam_user VALUES(default,default,?,?,?,?,?,?,?);";
        String actualQuery = userDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnUserUpdateQuery() {
        String expectedQuery = "UPDATE epam_user SET username = ?, password = ?, email = ?, first_name = ?, last_name = ?, is_activated = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = userDao.getUpdateQuery(expectedUser);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateUserInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyBoolean(),
                    Mockito.anyLong()
            )).thenAnswer(invocation -> null);
            userDao.populateInsertQuery(preparedStatement, expectedUser);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("username"),
                    Mockito.eq("dsfdsfgsdgfdfghfdgh"),
                    Mockito.eq("email@dsf.ds"),
                    Mockito.eq("firstname"),
                    Mockito.eq("lastname"),
                    Mockito.eq(true),
                    Mockito.eq(null)
            ));
        }
    }

    @Test
    void shouldPopulateUserUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyBoolean(),
                    Mockito.anyLong()
            )).thenAnswer(invocation -> null);
            userDao.populateUpdateQuery(preparedStatement, expectedUser);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("username"),
                    Mockito.eq("dsfdsfgsdgfdfghfdgh"),
                    Mockito.eq("email@dsf.ds"),
                    Mockito.eq("firstname"),
                    Mockito.eq("lastname"),
                    Mockito.eq(true)
            ));
        }
    }
}
