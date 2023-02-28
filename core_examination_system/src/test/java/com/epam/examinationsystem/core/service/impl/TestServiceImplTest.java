package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.service.UserTestService;
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
class TestServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImplTest.class);

    @Mock
    private TestDao testDao;

    @Mock
    private SubjectDao subjectDao;

    @Mock
    private QuestionService questionService;

    @Mock
    private UserTestService userTestService;

    @Mock
    private TransactionManager<com.epam.examinationsystem.core.entity.Test> transactionManager;

    @InjectMocks
    private TestServiceImpl testService;

    private UUID uuid;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private Subject expectedSubject;
    private TestDto.SubjectForTest expectedSubjectForTestDto;
    private StudentTestDto expectedStudentTestDto;
    private StudentTestDto.QuestionForStudentTestDto expectedQuestionForStudentTestDto;
    private TestDto expectedTestDto;
    private List<com.epam.examinationsystem.core.entity.Test> expectedTests;
    private List<TestDto> expectedTestDtos;
    private DataTableRequest request;
    private DataTableResponse<TestDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", TestServiceImpl.class.getSimpleName());
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedSubject = Subject.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .build();
        expectedSubjectForTestDto = new TestDto.SubjectForTest(
                "00000000-0000-0000-0000-000000000001",
                "name");
        expectedQuestionForStudentTestDto = new StudentTestDto.QuestionForStudentTestDto(
                "00000000-0000-0000-0000-000000000001",
                "easy",
                "content",
                "description",
                Collections.emptyList()
        );
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .setComplexity(TestComplexity.EASY)
                .setDuration(100)
                .setTotalAttemptNumber(0)
                .setExpirationDate(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setCreated(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setMaxAttemptNumber(1)
                .setSubject(expectedSubject)
                .build();
        expectedTestDto = TestDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .setComplexity("easy")
                .setDuration("100")
                .setTotalAttemptNumber("0")
                .setExpirationDate("2015-08-04T10:11:30")
                .setCreationDate("2015-08-04T10:11:30")
                .setMaxAttemptNumber("1")
                .setSubject(expectedSubjectForTestDto)
                .build();
        expectedStudentTestDto = StudentTestDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .setComplexity("easy")
                .setDuration("100")
                .setTotalAttemptNumber("0")
                .setExpirationDate("2015-08-04T10:11:30")
                .setCreationDate("2015-08-04T10:11:30")
                .setMaxAttemptNumber("1")
                .build();
        expectedTests = List.of(expectedTest);
        expectedTestDtos = List.of(expectedTestDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedTestDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", TestServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldCreateTest() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.create(Mockito.any(com.epam.examinationsystem.core.entity.Test.class))).thenReturn(expectedTest);
        Mockito.when(subjectDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedSubject));

        boolean actualCreateState = testService.create(expectedTestDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<com.epam.examinationsystem.core.entity.Test> testCaptor = ArgumentCaptor.forClass(com.epam.examinationsystem.core.entity.Test.class);
        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).create(testCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals("name", testCaptor.getValue().getName());
        Assertions.assertEquals("description", testCaptor.getValue().getDescription());
        Assertions.assertEquals(TestComplexity.EASY, testCaptor.getValue().getComplexity());
        Assertions.assertEquals(100, testCaptor.getValue().getDuration());
        Assertions.assertEquals(0, testCaptor.getValue().getTotalAttemptNumber());
        Assertions.assertEquals(LocalDateTime.parse("2015-08-04T10:11:30"), testCaptor.getValue().getExpirationDate());
        Assertions.assertTrue(actualCreateState);
    }

    @Test
    void shouldUpdateTest() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.update(Mockito.any(com.epam.examinationsystem.core.entity.Test.class))).thenReturn(expectedTest);
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));

        boolean actualUpdateState = testService.update(expectedTestDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<com.epam.examinationsystem.core.entity.Test> testCaptor = ArgumentCaptor.forClass(com.epam.examinationsystem.core.entity.Test.class);
        Mockito.verify(testDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).update(testCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedTest, testCaptor.getValue());
        Assertions.assertTrue(actualUpdateState);
    }

    @Test
    void shouldFindTestByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));

        Optional<TestDto> actualTestDto = testService.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(testDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedTestDto), actualTestDto);
    }

    @Test
    void shouldFindAllTestsBySubjectUuidForStudent() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.findAllBySubjectUuid(Mockito.any(UUID.class))).thenReturn(expectedTests);

        List<com.epam.examinationsystem.core.entity.Test> actualTests = testService.findAllBySubjectUuidForStudent(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(testDao, Mockito.times(1)).findAllBySubjectUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedTests, actualTests);
    }

    @Test
    void shouldFindAllTestsByUuidForTesting() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.findByUuidWithoutSubject(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(questionService.findAllByTestUuid(Mockito.any(UUID.class))).thenReturn(List.of(expectedQuestionForStudentTestDto));

        Optional<StudentTestDto> actualTests = testService.findByUuidForTesting(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(testDao, Mockito.times(1)).findByUuidWithoutSubject(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedStudentTestDto), actualTests);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingTestByUuidIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = testService.existsByUuid(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfTestExistsByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.when(testDao.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        boolean actualExistence = testService.existsByUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(testDao, Mockito.times(1)).existsByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldFindAllTestsByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(testDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedTests);
            Mockito.when(testDao.findAll()).thenReturn(Collections.emptyList());
            Mockito.when(testDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<TestDto> actualDataTableTestResponse = testService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(testDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(testDao, Mockito.times(1)).count(requestCaptor.capture());
            Mockito.verify(testDao, Mockito.times(1)).findAll();

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(response, actualDataTableTestResponse);
        }
    }

    @Test
    void shouldFindAllTestsForStudentByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(testDao.findAllForStudent(Mockito.any(DataTableRequest.class))).thenReturn(expectedTests);
            Mockito.when(testDao.findAllForStudent()).thenReturn(Collections.emptyList());
            Mockito.when(testDao.countForStudent(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            Mockito.when(userTestService.isSelected(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(true);
            Mockito.when(userTestService.getCurrentAttemptNumber(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(1);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<TestDto> actualDataTableTestResponse = testService.findAllForStudent(request, uuid);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(testDao, Mockito.times(1)).findAllForStudent(requestCaptor.capture());
            Mockito.verify(testDao, Mockito.times(1)).findAllForStudent();
            Mockito.verify(testDao, Mockito.times(1)).countForStudent(requestCaptor.capture());
            Mockito.verify(userTestService, Mockito.times(1)).isSelected(Mockito.any(), Mockito.any());
            Mockito.verify(userTestService, Mockito.times(1)).getCurrentAttemptNumber(Mockito.any(), Mockito.any());

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(response, actualDataTableTestResponse);
        }
    }

    @Test
    void shouldFindAllTests() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.findAll()).thenReturn(expectedTests);
        List<TestDto> actualDataTableTestResponse = testService.findAll();
        Mockito.verify(testDao, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedTestDtos, actualDataTableTestResponse);
    }

    @Test
    void shouldDeleteTestByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(testDao.deleteByUuid(Mockito.any(UUID.class))).thenReturn(true);

        boolean actualDeleteStatus = testService.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(testDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeleteStatus);
    }
}
