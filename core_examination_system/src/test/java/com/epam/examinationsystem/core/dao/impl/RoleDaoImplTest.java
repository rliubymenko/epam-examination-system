package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
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
class RoleDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(RoleDaoImplTest.class);

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
    private RoleDaoImpl roleDao;

    private long id;
    private UUID uuid;
    private Role expectedRole;
    List<Role> expectedRoles;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", RoleDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedRole = Role.builder()
                .setUuid(uuid)
                .setName(UserType.ADMIN)
                .build();
        expectedRoles = List.of(expectedRole);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", RoleDaoImplTest.class.getSimpleName());
    }

    @Test
    void shouldFindRoleByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedRole).when(roleDao).extractEntity(resultSet);

        Optional<Role> actualRole = roleDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(roleDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedRole), actualRole);
    }

    @Test
    void shouldFindRoleByUserType() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedRole).when(roleDao).extractEntity(resultSet);

        Optional<Role> actualRole = roleDao.findByUserType(UserType.ADMIN);

        ArgumentCaptor<UserType> userTypeCaptor = ArgumentCaptor.forClass(UserType.class);

        Mockito.verify(roleDao, Mockito.times(1)).findByUserType(userTypeCaptor.capture());
        Assertions.assertEquals(UserType.ADMIN, userTypeCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedRole), actualRole);
    }

    @Test
    void shouldGetRoleById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedRole).when(roleDao).extractEntity(resultSet);

        Role actualRole = roleDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(roleDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedRole, actualRole);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = roleDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllRoles() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedRoles).when(roleDao).extractEntities(resultSet);

        List<Role> actualRoles = roleDao.findAll();

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void shouldFindAllRolesByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedRoles).when(roleDao).extractEntities(resultSet);

        List<Role> actualRoles = roleDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(roleDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void shouldCreateRole() {
        Role actualRole = roleDao.create(expectedRole);

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);

        Mockito.verify(roleDao, Mockito.times(1)).create(roleCaptor.capture());
        Assertions.assertEquals(expectedRole, roleCaptor.getValue());

        Assertions.assertNull(actualRole);
    }

    @Test
    void shouldUpdateRole() {
        Role actualRole = roleDao.update(expectedRole);

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);

        Mockito.verify(roleDao, Mockito.times(1)).update(roleCaptor.capture());
        Assertions.assertEquals(expectedRole, roleCaptor.getValue());

        Assertions.assertNull(actualRole);
    }

    @Test
    void shouldDeleteRoleByUuid() {
        boolean actualDeletionStatus = roleDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(roleDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertFalse(actualDeletionStatus);
    }

    @Test
    void shouldCountRolesByRequest() {
        long actualNumber = roleDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(roleDao, Mockito.times(1)).count(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(3L, actualNumber);
    }

    @Test
    void shouldExtractRole() throws SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractRole(resultSet)).thenReturn(expectedRole);
            Role actualRole = roleDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedRole, actualRole);
        }
    }

    @Test
    void shouldExtractRoles() throws SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractRole(resultSet)).thenReturn(expectedRole);
            List<Role> actualRoles = roleDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedRoles, actualRoles);
        }
    }

    @Test
    void shouldReturnRoleInsertQuery() {
        String actualQuery = roleDao.getInsertQuery();
        Assertions.assertNull(actualQuery);
    }

    @Test
    void shouldReturnRoleUpdateQuery() {
        String actualQuery = roleDao.getUpdateQuery(expectedRole);
        Assertions.assertNull(actualQuery);
    }

    @Test
    void shouldPopulateRoleInsertQuery() {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            roleDao.populateInsertQuery(preparedStatement, expectedRole);
            daoMapperUtil.verifyNoInteractions();
        }
    }

    @Test
    void shouldPopulateRoleUpdateQuery() {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            roleDao.populateUpdateQuery(preparedStatement, expectedRole);
            daoMapperUtil.verifyNoInteractions();
        }
    }
}
