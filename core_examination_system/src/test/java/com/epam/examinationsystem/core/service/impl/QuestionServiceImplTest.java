package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionServiceImplTest.class);

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private TestDao testDao;

    @Mock
    private TransactionManager<Question> transactionManager;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private UUID uuid;
    private Question expectedQuestion;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private QuestionDto.TestForQuestion testForQuestion;
    private StudentTestDto.QuestionForStudentTestDto questionForStudentTestDto;
    private QuestionDto expectedQuestionDto;
    private List<Question> expectedQuestions;
    private List<QuestionDto> expectedQuestionDtos;
    private List<StudentTestDto.QuestionForStudentTestDto> questionForStudentTestDtos;
    private DataTableRequest request;
    private DataTableResponse<QuestionDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", QuestionServiceImpl.class.getSimpleName());
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .setComplexity(TestComplexity.EASY)
                .setDuration(100)
                .setTotalAttemptNumber(0)
                .setExpirationDate(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setMaxAttemptNumber(1)
                .setSubject(null)
                .build();
        testForQuestion = new QuestionDto.TestForQuestion("00000000-0000-0000-0000-000000000001", "name");
        questionForStudentTestDto = new StudentTestDto.QuestionForStudentTestDto(
                "00000000-0000-0000-0000-000000000001",
                "text",
                "content",
                "description",
                Collections.emptyList()
        );
        expectedQuestion = Question.builder()
                .setUuid(uuid)
                .setContent("content")
                .setDescription("description")
                .setType(QuestionType.TEXT)
                .setTest(expectedTest)
                .build();
        expectedQuestionDto = QuestionDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setContent("content")
                .setDescription("description")
                .setType("text")
                .setTest(testForQuestion)
                .build();
        expectedQuestions = List.of(expectedQuestion);
        expectedQuestionDtos = List.of(expectedQuestionDto);
        questionForStudentTestDtos = List.of(questionForStudentTestDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedQuestionDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", QuestionServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldCreateQuestion() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(questionDao.create(Mockito.any(Question.class))).thenReturn(expectedQuestion);

        QuestionDto actualQuestionDto = questionService.create(expectedQuestionDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);

        Mockito.verify(testDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).create(questionCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertNull(questionCaptor.getValue().getUuid());
        Assertions.assertEquals(expectedQuestionDto, actualQuestionDto);
    }

    @Test
    void shouldUpdateQuestion() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedQuestion));
        Mockito.when(questionDao.update(Mockito.any(Question.class))).thenReturn(expectedQuestion);

        boolean actualUpdateState = questionService.update(expectedQuestionDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Question> questionCaptor = ArgumentCaptor.forClass(Question.class);

        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).update(questionCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedQuestion, questionCaptor.getValue());
        Assertions.assertTrue(actualUpdateState);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingQuestionByUuidIfIncomingUuidIsNull() throws ServiceException {
        Optional<QuestionDto> actualQuestionDto = questionService.findByUuid(null);
        Assertions.assertTrue(actualQuestionDto.isEmpty());
    }

    @Test
    void shouldFindQuestionByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedQuestion));

        Optional<QuestionDto> actualQuestionDto = questionService.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedQuestionDto), actualQuestionDto);
    }

    @Test
    void shouldReturnFalseWhileCheckingIfExistsByUuid() throws ServiceException {
        boolean actualExistence = questionService.existsByUuid(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfQuestionExistsByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any());
        Mockito.when(questionDao.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        boolean actualExistence = questionService.existsByUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(questionDao, Mockito.times(1)).existsByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldFindAllQuestionsByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(questionDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedQuestions);
            Mockito.when(questionDao.findAll()).thenReturn(expectedQuestions);
            Mockito.when(questionDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<QuestionDto> actualDataTableQuestionResponse = questionService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(questionDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(questionDao, Mockito.times(1)).count(requestCaptor.capture());

            List<DataTableRequest> capturedQuestions = requestCaptor.getAllValues();
            Assertions.assertEquals(request, capturedQuestions.get(0));
            Assertions.assertEquals(request, capturedQuestions.get(1));
            Assertions.assertEquals(response, actualDataTableQuestionResponse);
        }
    }

    @Test
    void shouldFindAllQuestions() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findAll()).thenReturn(expectedQuestions);

        List<QuestionDto> actualQuestionDtos = questionService.findAll();

        Assertions.assertEquals(expectedQuestionDtos, actualQuestionDtos);
    }

    @Test
    void shouldFindAllQuestionsByTestUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findAllByTestUuid(Mockito.any(UUID.class))).thenReturn(expectedQuestions);
        Mockito.when(answerDao.findAllByQuestionUuid(Mockito.any(UUID.class))).thenReturn(Collections.emptyList());

        List<StudentTestDto.QuestionForStudentTestDto> actualQuestionDtos = questionService.findAllByTestUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(questionDao, Mockito.times(1)).findAllByTestUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertEquals(questionForStudentTestDtos, actualQuestionDtos);
    }

    @Test
    void shouldFindAllOpenToCreateAnswers() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findAll()).thenReturn(expectedQuestions);
        Mockito.when(answerDao.findAllByQuestionUuid(Mockito.any(UUID.class))).thenReturn(Collections.emptyList());

        List<QuestionDto> actualQuestionDtos = questionService.findAllOpenToCreateAnswers();

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(answerDao, Mockito.times(1)).findAllByQuestionUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertEquals(expectedQuestionDtos, actualQuestionDtos);
    }

    @Test
    void shouldDeleteAnswerByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.deleteByUuid(Mockito.any(UUID.class))).thenReturn(true);

        boolean actualDeleteStatus = questionService.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(questionDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeleteStatus);
    }
}
