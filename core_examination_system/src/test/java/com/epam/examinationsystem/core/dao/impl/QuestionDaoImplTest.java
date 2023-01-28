package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.QuestionType;
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
class QuestionDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionDaoImplTest.class);

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
    private QuestionDaoImpl questionDao;

    private long id;
    private UUID uuid;
    private Question expectedQuestion;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private List<Question> expectedQuestions;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", QuestionDaoImplTest.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder().build();
        expectedQuestion = Question.builder()
                .setUuid(uuid)
                .setContent("content")
                .setDescription("description")
                .setType(QuestionType.TEXT)
                .setTest(expectedTest)
                .build();

        expectedQuestions = List.of(expectedQuestion);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", QuestionDaoImplTest.class.getSimpleName());
    }

    @Test
    void shouldFindQuestionByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedQuestion).when(questionDao).extractEntity(resultSet);

        Optional<Question> actualQuestion = questionDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedQuestion), actualQuestion);
    }

    @Test
    void shouldGetQuestionById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedQuestion).when(questionDao).extractEntity(resultSet);

        Question actualQuestion = questionDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(questionDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = questionDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllQuestions() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedQuestions).when(questionDao).extractEntities(resultSet);

        List<Question> actualQuestions = questionDao.findAll();

        Assertions.assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void shouldFindAllQuestionsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedQuestions).when(questionDao).extractEntities(resultSet);

        List<Question> actualQuestions = questionDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(questionDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void shouldCreateQuestion() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(questionDao).populateInsertQuery(preparedStatement, expectedQuestion);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedQuestion).when(questionDao).getById(id);

        Question actualQuestion = questionDao.create(expectedQuestion);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(questionDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).create(questionCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedQuestion, questionCaptor.getValue());

        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void shouldUpdateQuestion() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(questionDao).populateUpdateQuery(preparedStatement, expectedQuestion);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedQuestion)).when(questionDao).findByUuid(uuid);

        Question actualQuestion = questionDao.update(expectedQuestion);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);

        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).update(questionCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedQuestion, questionCaptor.getValue());

        Assertions.assertEquals(expectedQuestion, actualQuestion);
    }

    @Test
    void shouldDeleteQuestionByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = questionDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(questionDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountQuestionsByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = questionDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(questionDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldFindAllByTestUuid() throws DaoException, SQLException {
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedQuestions).when(questionDao).extractEntities(resultSet);

        List<Question> actualQuestions = questionDao.findAllByTestUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(questionDao, Mockito.times(1)).findAllByTestUuid(uuidCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());
        Assertions.assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void shouldExtractQuestion() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractQuestion(resultSet, testDao)).thenReturn(expectedQuestion);
            Question actualQuestion = questionDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedQuestion, actualQuestion);
        }
    }

    @Test
    void shouldExtractQuestions() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractQuestion(resultSet, testDao)).thenReturn(expectedQuestion);
            List<Question> actualQuestions = questionDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedQuestions, actualQuestions);
        }
    }

    @Test
    void shouldReturnQuestionInsertQuery() {
        String expectedQuery = "INSERT INTO question VALUES(default,default,?,?,?,?);";
        String actualQuery = questionDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnQuestionUpdateQuery() {
        String expectedQuery = "UPDATE question SET type = ?, content = ?, description = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = questionDao.getUpdateQuery(expectedQuestion);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateQuestionInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.any(QuestionType.class),
                    Mockito.anyString(),
                    Mockito.anyString(),
                    Mockito.anyLong()
            )).thenAnswer(invocation -> null);
            questionDao.populateInsertQuery(preparedStatement, expectedQuestion);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq(QuestionType.TEXT),
                    Mockito.eq("content"),
                    Mockito.eq("description"),
                    Mockito.eq(null)
            ));
        }
    }

    @Test
    void shouldPopulateQuestionUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.any(QuestionType.class),
                    Mockito.anyString(),
                    Mockito.anyString()
            )).thenAnswer(invocation -> null);
            questionDao.populateUpdateQuery(preparedStatement, expectedQuestion);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement),
                    Mockito.eq(QuestionType.TEXT),
                    Mockito.eq("content"),
                    Mockito.eq("description")
            ));
        }
    }
}
