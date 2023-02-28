package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.QuestionType;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerServiceImplTest.class);

    @Mock
    private AnswerDao answerDao;

    @Mock
    private QuestionDao questionDao;

    @Mock
    private TransactionManager<Answer> transactionManager;

    @InjectMocks
    private AnswerServiceImpl answerService;

    private UUID uuid;
    private Answer expectedAnswer;
    private Answer expectedAnswerForSingleChoice;
    private AnswerDto expectedAnswerDto;
    private List<Answer> expectedAnswers;
    private List<AnswerDto> expectedAnswerDtos;
    private Question expectedQuestion;
    private Question expectedAnswerSingleChoiceQuestion;
    private AnswerDto.QuestionForAnswer expectedAnswerQuestionDto;
    private QuestionDto expectedQuestionDto;
    private DataTableRequest request;
    private DataTableResponse<AnswerDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", AnswerServiceImpl.class.getSimpleName());
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedQuestion = Question.builder()
                .setUuid(uuid)
                .setContent("content")
                .setDescription("description")
                .setType(QuestionType.TEXT)
                .setTest(null)
                .build();
        expectedAnswerSingleChoiceQuestion = Question.builder()
                .setUuid(uuid)
                .setContent("content")
                .setDescription("description")
                .setType(QuestionType.SINGLE_CHOICE)
                .setTest(null)
                .build();

        expectedAnswerQuestionDto = new AnswerDto.QuestionForAnswer(
                "00000000-0000-0000-0000-000000000001",
                "content",
                QuestionType.TEXT.toString()
        );
        expectedQuestionDto = QuestionDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setContent("content")
                .setType(QuestionType.TEXT.toString())
                .build();
        expectedAnswer = Answer.builder()
                .setUuid(uuid)
                .setContent("content")
                .setIsCorrect(true)
                .setQuestion(expectedQuestion)
                .build();
        expectedAnswerForSingleChoice = Answer.builder()
                .setUuid(uuid)
                .setContent("content")
                .setIsCorrect(false)
                .setQuestion(expectedAnswerSingleChoiceQuestion)
                .build();
        expectedAnswerDto = AnswerDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setContent("content")
                .setIsCorrect("true")
                .setQuestion(expectedAnswerQuestionDto)
                .build();
        expectedAnswers = List.of(expectedAnswer);
        expectedAnswerDtos = List.of(expectedAnswerDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedAnswerDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", AnswerServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldReturnEmptyOptionalIfIncomingUuidIsNull() throws ServiceException {
        Optional<AnswerDto> actualAnswerDto = answerService.findByUuid(null);
        Assertions.assertTrue(actualAnswerDto.isEmpty());
    }

    @Test
    void shouldFindAnswerByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswer));

        Optional<AnswerDto> actualAnswerDto = answerService.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedAnswerDto), actualAnswerDto);
    }

    @Test
    void shouldFindAllAnswersByQuestionUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findAllByQuestionUuid(Mockito.any(UUID.class))).thenReturn(expectedAnswers);

        List<AnswerDto> actualAnswerDtos = answerService.findAllByQuestionUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(answerDao, Mockito.times(1)).findAllByQuestionUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedAnswerDtos, actualAnswerDtos);
    }

    @Test
    void shouldCreateAnswersForQuestion() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(questionDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedQuestion));
        Mockito.when(answerDao.create(Mockito.any(Answer.class))).thenReturn(expectedAnswer);

        answerService.createAnswersForQuestion(expectedAnswerDtos, expectedQuestionDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).create(answerCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals("content", answerCaptor.getValue().getContent());
        Assertions.assertTrue(answerCaptor.getValue().getIsCorrect());
        Assertions.assertEquals(expectedQuestion, answerCaptor.getValue().getQuestion());
    }

    @Test
    void shouldUpdateNotSingleChoiceAnswer() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswer));
        Mockito.when(answerDao.update(Mockito.any(Answer.class))).thenReturn(expectedAnswer);

        boolean actualUpdateResult = answerService.update(expectedAnswerDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).update(answerCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedAnswer, answerCaptor.getValue());
        Assertions.assertTrue(actualUpdateResult);
    }

    @Test
    void shouldUpdateSingleChoiceAnswer() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswerForSingleChoice));
        Mockito.when(answerDao.update(Mockito.any(Answer.class))).thenReturn(expectedAnswerForSingleChoice);
        Mockito.when(answerDao.findAllByQuestionUuid(Mockito.any(UUID.class))).thenReturn(expectedAnswers);

        boolean actualUpdateResult = answerService.update(expectedAnswerDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(2)).update(answerCaptor.capture());

        List<Answer> capturedAnswers = answerCaptor.getAllValues();
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertFalse(capturedAnswers.get(0).getIsCorrect());
        Assertions.assertTrue(capturedAnswers.contains(expectedAnswer));
        Assertions.assertTrue(actualUpdateResult);
    }

    @Test
    void shouldUpdateAnswerAndSetNewTrueAnswer() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswer));
        Mockito.when(answerDao.update(Mockito.any(Answer.class))).thenReturn(expectedAnswer);

        boolean actualUpdateResult = answerService.updateAnswerAndSetNewTrueAnswer(expectedAnswerDto, uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);
        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(2)).update(answerCaptor.capture());

        List<Answer> capturedAnswers = answerCaptor.getAllValues();
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(capturedAnswers.get(0).getIsCorrect());
        Assertions.assertFalse(capturedAnswers.get(1).getIsCorrect());
        Assertions.assertTrue(actualUpdateResult);
    }

    @Test
    void shouldFindAllAnswersByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(answerDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedAnswers);
            Mockito.when(answerDao.findAll()).thenReturn(expectedAnswers);
            Mockito.when(answerDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<AnswerDto> actualDataTableAnswerResponse = answerService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(answerDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(answerDao, Mockito.times(1)).count(requestCaptor.capture());

            List<DataTableRequest> capturedAnswers = requestCaptor.getAllValues();
            Assertions.assertEquals(request, capturedAnswers.get(0));
            Assertions.assertEquals(request, capturedAnswers.get(1));
            Assertions.assertEquals(response, actualDataTableAnswerResponse);
        }
    }

    @Test
    void shouldFindAllAnswers() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findAll()).thenReturn(expectedAnswers);

        List<AnswerDto> actualAnswerDtos = answerService.findAll();

        Assertions.assertEquals(expectedAnswerDtos, actualAnswerDtos);
    }

    @Test
    void shouldDeleteAnswersByUuidAndQuestionUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswer));
        Mockito.when(questionDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedQuestion));
        Mockito.when(answerDao.deleteByUuid(Mockito.any(UUID.class))).thenReturn(true);

        boolean actualUpdateResult = answerService.deleteByUuidAndQuestionUuid(uuid, uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(answerDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(questionDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());

        List<UUID> capturedUuids = uuidCaptor.getAllValues();
        Assertions.assertEquals(uuid, capturedUuids.get(0));
        Assertions.assertEquals(uuid, capturedUuids.get(1));
        Assertions.assertEquals(uuid, capturedUuids.get(2));
        Assertions.assertTrue(actualUpdateResult);
    }

    @Test
    void shouldDeleteByUuidAndSetNewTrueAnswer() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(answerDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedAnswer));
        Mockito.when(answerDao.update(Mockito.any(Answer.class))).thenReturn(expectedAnswer);
        Mockito.when(answerDao.deleteByUuid(Mockito.any(UUID.class))).thenReturn(true);

        boolean actualUpdateResult = answerService.deleteByUuidAndSetNewTrueAnswer(uuid, uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Answer> answerCaptor = ArgumentCaptor.forClass(Answer.class);

        Mockito.verify(answerDao, Mockito.times(2)).findByUuid(uuidCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).update(answerCaptor.capture());
        Mockito.verify(answerDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());

        List<UUID> capturedUuids = uuidCaptor.getAllValues();
        Assertions.assertEquals(uuid, capturedUuids.get(0));
        Assertions.assertEquals(uuid, capturedUuids.get(1));
        Assertions.assertEquals(uuid, capturedUuids.get(2));
        Assertions.assertEquals(true, answerCaptor.getValue().getIsCorrect());
        Assertions.assertTrue(actualUpdateResult);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingAnswerByUuidIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = answerService.existsByUuid(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfAnswerExistsByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any());
        Mockito.when(answerDao.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        boolean actualExistence = answerService.existsByUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(answerDao, Mockito.times(1)).existsByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }
}
