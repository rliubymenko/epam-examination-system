package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Subject;
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
class SubjectDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectDaoImplTest.class);

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
    private SubjectDaoImpl subjectDao;

    private long id;
    private UUID uuid;
    private Subject expectedSubject;
    private List<Subject> expectedSubjects;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", SubjectDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedSubject = Subject.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .build();
        expectedSubjects = List.of(expectedSubject);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", SubjectDaoImplTest.class.getSimpleName());
    }


    @Test
    void shouldFindSubjectByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedSubject).when(subjectDao).extractEntity(resultSet);

        Optional<Subject> actualSubject = subjectDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedSubject), actualSubject);
    }

    @Test
    void shouldGetSubjectById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedSubject).when(subjectDao).extractEntity(resultSet);

        Subject actualSubject = subjectDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(subjectDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = subjectDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldCheckIfExistByName() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = subjectDao.existsByName("name");

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllSubjects() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedSubjects).when(subjectDao).extractEntities(resultSet);

        List<Subject> actualSubjects = subjectDao.findAll();

        Assertions.assertEquals(expectedSubjects, actualSubjects);
    }

    @Test
    void shouldFindAllSubjectsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedSubjects).when(subjectDao).extractEntities(resultSet);

        List<Subject> actualSubjects = subjectDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(subjectDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedSubjects, actualSubjects);
    }

    @Test
    void shouldCreateSubject() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(subjectDao).populateInsertQuery(preparedStatement, expectedSubject);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedSubject).when(subjectDao).getById(id);

        Subject actualSubject = subjectDao.create(expectedSubject);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Subject> subjectCaptor = ArgumentCaptor.forClass(Subject.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(subjectDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(subjectDao, Mockito.times(1)).create(subjectCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedSubject, subjectCaptor.getValue());

        Assertions.assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void shouldUpdateSubject() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(subjectDao).populateUpdateQuery(preparedStatement, expectedSubject);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedSubject)).when(subjectDao).findByUuid(uuid);

        Subject actualSubject = subjectDao.update(expectedSubject);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Subject> subjectCaptor = ArgumentCaptor.forClass(Subject.class);

        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(subjectDao, Mockito.times(1)).update(subjectCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedSubject, subjectCaptor.getValue());

        Assertions.assertEquals(expectedSubject, actualSubject);
    }

    @Test
    void shouldDeleteSubjectByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = subjectDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(subjectDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountSubjectsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = subjectDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(subjectDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldExtractSubject() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractSubject(resultSet)).thenReturn(expectedSubject);
            Subject actualSubject = subjectDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedSubject, actualSubject);
        }
    }

    @Test
    void shouldExtractSubjects() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractSubject(resultSet)).thenReturn(expectedSubject);
            List<Subject> actualSubjects = subjectDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedSubjects, actualSubjects);
        }
    }

    @Test
    void shouldReturnSubjectInsertQuery() {
        String expectedQuery = "INSERT INTO subject VALUES(default,default,?,?);";
        String actualQuery = subjectDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnSubjectUpdateQuery() {
        String expectedQuery = "UPDATE subject SET name = ?, description = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = subjectDao.getUpdateQuery(expectedSubject);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateSubjectInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),

                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyLong()
            )).thenAnswer(invocation -> null);
            subjectDao.populateInsertQuery(preparedStatement, expectedSubject);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("name"),
                    Mockito.eq("description")
            ));
        }
    }

    @Test
    void shouldPopulateSubjectUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyString()
            )).thenAnswer(invocation -> null);
            subjectDao.populateUpdateQuery(preparedStatement, expectedSubject);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("name"),
                    Mockito.eq("description")
            ));
        }
    }
}
