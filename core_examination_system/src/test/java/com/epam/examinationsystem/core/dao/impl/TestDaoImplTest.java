package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
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
class TestDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TestDaoImplTest.class);

    @Mock
    private SubjectDao subjectDao;

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
    private TestDaoImpl testDao;

    private long id;
    private UUID uuid;
    private Subject expectedSubject;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private List<com.epam.examinationsystem.core.entity.Test> expectedTests;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", TestDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedSubject = Subject.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .build();
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .setComplexity(TestComplexity.EASY)
                .setDuration(100)
                .setTotalAttemptNumber(0)
                .setExpirationDate(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setMaxAttemptNumber(1)
                .setSubject(expectedSubject)
                .build();
        expectedTests = List.of(expectedTest);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", TestDaoImplTest.class.getSimpleName());
    }

    @Test
    void shouldFindTestByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTest).when(testDao).extractEntity(resultSet);

        Optional<com.epam.examinationsystem.core.entity.Test> actualTest = testDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedTest), actualTest);
    }

    @Test
    void shouldGetTestById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTest).when(testDao).extractTestWithoutSubject(resultSet);

        com.epam.examinationsystem.core.entity.Test actualTest = testDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(testDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedTest, actualTest);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = testDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllTests() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTests).when(testDao).extractEntities(resultSet);

        List<com.epam.examinationsystem.core.entity.Test> actualTests = testDao.findAll();

        Assertions.assertEquals(expectedTests, actualTests);
    }

    @Test
    void shouldFindAllTestsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTests).when(testDao).extractEntities(resultSet);

        List<com.epam.examinationsystem.core.entity.Test> actualTests = testDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(testDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedTests, actualTests);
    }

    @Test
    void shouldCreateTest() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(testDao).populateInsertQuery(preparedStatement, expectedTest);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedTest).when(testDao).getById(id);

        com.epam.examinationsystem.core.entity.Test actualTest = testDao.create(expectedTest);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<com.epam.examinationsystem.core.entity.Test> testCaptor = ArgumentCaptor.forClass(com.epam.examinationsystem.core.entity.Test.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(testDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).create(testCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedTest, testCaptor.getValue());

        Assertions.assertEquals(expectedTest, actualTest);
    }

    @Test
    void shouldUpdateTest() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(testDao).populateUpdateQuery(preparedStatement, expectedTest);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedTest)).when(testDao).findByUuid(uuid);

        com.epam.examinationsystem.core.entity.Test actualTest = testDao.update(expectedTest);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<com.epam.examinationsystem.core.entity.Test> testCaptor = ArgumentCaptor.forClass(com.epam.examinationsystem.core.entity.Test.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).update(testCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedTest, testCaptor.getValue());

        Assertions.assertEquals(expectedTest, actualTest);
    }

    @Test
    void shouldDeleteTestByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = testDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountTestsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = testDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(testDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldExtractTest() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractTest(resultSet, subjectDao)).thenReturn(expectedTest);
            com.epam.examinationsystem.core.entity.Test actualTest = testDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedTest, actualTest);
        }
    }

    @Test
    void shouldExtractTests() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractTest(resultSet, subjectDao)).thenReturn(expectedTest);
            List<com.epam.examinationsystem.core.entity.Test> actualTests = testDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedTests, actualTests);
        }
    }


    @Test
    void shouldExtractTestWithoutSubject() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractTestWithoutSubject(resultSet)).thenReturn(expectedTest);
            com.epam.examinationsystem.core.entity.Test actualTest = testDao.extractTestWithoutSubject(resultSet);
            Assertions.assertEquals(expectedTest, actualTest);
        }
    }

    @Test
    void shouldExtractTestsWithoutSubject() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractTestWithoutSubject(resultSet)).thenReturn(expectedTest);
            List<com.epam.examinationsystem.core.entity.Test> actualTests = testDao.extractTestsWithoutSubject(resultSet);
            Assertions.assertEquals(expectedTests, actualTests);
        }
    }

    @Test
    void shouldReturnTestInsertQuery() {
        String expectedQuery = "INSERT INTO test VALUES(default,default,?,?,?,?,?,?,?,?);";
        String actualQuery = testDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnTestUpdateQuery() {
        String expectedQuery = "UPDATE test SET name = ?, description = ?, complexity = ?, duration = ?, expiration_date = ?, max_attempt_number = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = testDao.getUpdateQuery(expectedTest);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateTestInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.any(TestComplexity.class),
                    Mockito.anyInt(),
                    Mockito.anyInt(),
                    Mockito.anyLong(),
                    Mockito.any(LocalDateTime.class),
                    Mockito.anyInt()
            )).thenAnswer(invocation -> null);
            testDao.populateInsertQuery(preparedStatement, expectedTest);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("name"),
                    Mockito.eq("description"),
                    Mockito.eq(TestComplexity.EASY),
                    Mockito.eq(100),
                    Mockito.eq(0),
                    Mockito.eq(null),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30")),
                    Mockito.eq(1)
            ));
        }
    }

    @Test
    void shouldPopulateTestUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.any(TestComplexity.class),
                    Mockito.anyInt(),
                    Mockito.any(LocalDateTime.class),
                    Mockito.anyInt()
            )).thenAnswer(invocation -> null);
            testDao.populateUpdateQuery(preparedStatement, expectedTest);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq("name"),
                    Mockito.eq("description"),
                    Mockito.eq(TestComplexity.EASY),
                    Mockito.eq(100),
                    Mockito.eq(LocalDateTime.parse("2015-08-04T10:11:30")),
                    Mockito.eq(1)
            ));
        }
    }

    @Test
    void shouldIncrementTotalAttemptNumber() throws SQLException, DaoException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean isIncremented = testDao.incrementTotalAttemptNumber(expectedTest);

        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> columnDataCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(preparedStatement, Mockito.times(1)).setInt(columIndexCaptor.capture(), columnDataCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(1, columnDataCaptor.getValue());

        Assertions.assertTrue(isIncremented);
    }

    @Test
    void shouldFindTestByUuidWithoutSubject() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTest).when(testDao).extractTestWithoutSubject(resultSet);

        Optional<com.epam.examinationsystem.core.entity.Test> actualTest = testDao.findByUuidWithoutSubject(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuidWithoutSubject(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedTest), actualTest);
    }

    @Test
    void shouldFindAllTestsBySubjectUuid() throws DaoException, SQLException {
        Mockito.when(subjectDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedSubject));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedTests).when(testDao).extractTestsWithoutSubject(resultSet);

        List<com.epam.examinationsystem.core.entity.Test> actualTests = testDao.findAllBySubjectUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> subjectUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(testDao, Mockito.times(1)).findAllBySubjectUuid(uuidCaptor.capture());
        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(subjectUuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(uuid, subjectUuidCaptor.getValue());
        Assertions.assertEquals(expectedTests, actualTests);
    }
}
