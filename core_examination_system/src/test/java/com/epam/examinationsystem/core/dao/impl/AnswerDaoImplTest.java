package com.epam.examinationsystem.core.dao.impl;

import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.Answer;
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
class AnswerDaoImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerDaoImplTest.class);

    @Mock
    private QuestionDao questionDao;

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
    private AnswerDaoImpl answerDao;

    private long id;
    private UUID uuid;
    private Answer expectedAnswer;
    private List<Answer> expectedAnswers;
    private Question expectedQuestion;
    private DataTableRequest request;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", AnswerDaoImpl.class.getSimpleName());
        id = 1;
        uuid = UUID.fromString("00000000-000-0000-0000-000000000001");
        expectedQuestion = Question.builder()
                .setUuid(uuid)
                .setContent("content")
                .setDescription("description")
                .setType(QuestionType.TEXT)
                .setTest(null)
                .build();
        expectedAnswer = Answer.builder()
                .setUuid(uuid)
                .setContent("content")
                .setIsCorrect(true)
                .setQuestion(expectedQuestion)
                .build();
        expectedAnswers = List.of(expectedAnswer);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", AnswerDaoImpl.class.getSimpleName());
    }

    @Test
    void shouldFindAnswerByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedAnswer).when(answerDao).extractEntity(resultSet);

        Optional<Answer> actualAnswer = answerDao.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedAnswer), actualAnswer);
    }

    @Test
    void shouldGetAnswerById() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedAnswer).when(answerDao).extractEntity(resultSet);

        Answer actualAnswer = answerDao.getById(id);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.verify(answerDao, Mockito.times(1)).getById(idCaptor.capture());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void shouldCheckIfExistByUuid() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(false);

        boolean actualExistence = answerDao.existsByUuid(uuid);

        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldFindAllAnswers() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedAnswers).when(answerDao).extractEntities(resultSet);

        List<Answer> actualAnswers = answerDao.findAll();

        Assertions.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    void shouldFindAllAnswersByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedAnswers).when(answerDao).extractEntities(resultSet);

        List<Answer> actualAnswers = answerDao.findAll(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);

        Mockito.verify(answerDao, Mockito.times(1)).findAll(requestCaptor.capture());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    void shouldCreateAnswer() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString(), Mockito.eq(Statement.RETURN_GENERATED_KEYS))).thenReturn(preparedStatement);
        Mockito.doNothing().when(answerDao).populateInsertQuery(preparedStatement, expectedAnswer);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(id);
        Mockito.doReturn(expectedAnswer).when(answerDao).getById(id);

        Answer actualAnswer = answerDao.create(expectedAnswer);

        ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        ArgumentCaptor<Integer> columIndexCaptor = ArgumentCaptor.forClass(Integer.class);

        Mockito.verify(answerDao, Mockito.times(1)).getById(idCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).create(answerCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(columIndexCaptor.capture());
        Assertions.assertEquals(1, columIndexCaptor.getValue());
        Assertions.assertEquals(id, idCaptor.getValue());
        Assertions.assertEquals(expectedAnswer, answerCaptor.getValue());

        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void shouldUpdateAnswer() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.doNothing().when(answerDao).populateUpdateQuery(preparedStatement, expectedAnswer);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doReturn(Optional.of(expectedAnswer)).when(answerDao).findByUuid(uuid);

        Answer actualAnswer = answerDao.update(expectedAnswer);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);

        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).update(answerCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedAnswer, answerCaptor.getValue());

        Assertions.assertEquals(expectedAnswer, actualAnswer);
    }

    @Test
    void shouldDeleteAnswerByUuid() throws DaoException, SQLException {
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean actualDeletionStatus = answerDao.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(answerDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeletionStatus);
    }

    @Test
    void shouldCountAnswersByRequest() throws DaoException, SQLException {
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong("count")).thenReturn(10L);

        long actualNumber = answerDao.count(request);

        ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
        ArgumentCaptor<String> countCaptor = ArgumentCaptor.forClass(String.class);

        Mockito.verify(answerDao, Mockito.times(1)).count(requestCaptor.capture());
        Mockito.verify(resultSet, Mockito.times(1)).getLong(countCaptor.capture());
        Assertions.assertEquals("count", countCaptor.getValue());
        Assertions.assertEquals(request, requestCaptor.getValue());

        Assertions.assertEquals(10L, actualNumber);
    }

    @Test
    void shouldFindAllByQuestionUuid() throws DaoException, SQLException {
        Mockito.when(questionDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedQuestion));
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.doReturn(expectedAnswers).when(answerDao).extractEntities(resultSet);

        List<Answer> actualAnswers = answerDao.findAllByQuestionUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> questionUuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(answerDao, Mockito.times(1)).findAllByQuestionUuid(uuidCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(questionUuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(uuid, questionUuidCaptor.getValue());
        Assertions.assertEquals(expectedAnswers, actualAnswers);
    }

    @Test
    void shouldExtractAnswer() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractAnswer(resultSet, questionDao)).thenReturn(expectedAnswer);
            Answer actualAnswer = answerDao.extractEntity(resultSet);
            Assertions.assertEquals(expectedAnswer, actualAnswer);
        }
    }

    @Test
    void shouldExtractAnswers() throws DaoException, SQLException {
        try (MockedStatic<DaoMapperUtil> daoMapperUtil = Mockito.mockStatic(DaoMapperUtil.class)) {
            Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
            daoMapperUtil.when(() -> DaoMapperUtil.extractAnswer(resultSet, questionDao)).thenReturn(expectedAnswer);
            List<Answer> actualAnswers = answerDao.extractEntities(resultSet);
            Assertions.assertEquals(expectedAnswers, actualAnswers);
        }
    }

    @Test
    void shouldReturnAnswerInsertQuery() {
        String expectedQuery = "INSERT INTO answer VALUES(default,default,?,?,?);";
        String actualQuery = answerDao.getInsertQuery();
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldReturnAnswerUpdateQuery() {
        String expectedQuery = "UPDATE answer SET content = ?, is_correct = ? WHERE uuid = '00000000-0000-0000-0000-000000000001';";
        String actualQuery = answerDao.getUpdateQuery(expectedAnswer);
        Assertions.assertEquals(expectedQuery, actualQuery);
    }

    @Test
    void shouldPopulateAnswerInsertQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyBoolean(),
                    Mockito.anyLong()
            )).thenAnswer(invocation -> null);
            answerDao.populateInsertQuery(preparedStatement, expectedAnswer);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement), Mockito.eq("content"), Mockito.eq(true), Mockito.eq(null))
            );
        }
    }

    @Test
    void shouldPopulateAnswerUpdateQuery() throws SQLException {
        try (MockedStatic<QueryBuilderUtil> queryBuilderUtil = Mockito.mockStatic(QueryBuilderUtil.class)) {
            queryBuilderUtil.when(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.any(PreparedStatement.class),
                    Mockito.anyString(),
                    Mockito.anyBoolean()
            )).thenAnswer(invocation -> null);
            answerDao.populateUpdateQuery(preparedStatement, expectedAnswer);
            queryBuilderUtil.verify(() -> QueryBuilderUtil.populatePreparedStatement(
                    Mockito.eq(preparedStatement), Mockito.eq("content"), Mockito.eq(true))
            );
        }
    }
}
