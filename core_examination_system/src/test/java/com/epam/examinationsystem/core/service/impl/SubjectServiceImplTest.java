package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentSubjectDto;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectServiceImplTest.class);

    @Mock
    private SubjectDao subjectDao;

    @Mock
    private TestService testService;

    @Mock
    private TransactionManager<Subject> transactionManager;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    private UUID uuid;
    private Subject expectedSubject;
    private SubjectDto expectedSubjectDto;
    private StudentSubjectDto expectedStudentSubjectDto;
    private List<Subject> expectedSubjects;
    private List<SubjectDto> expectedSubjectDtos;
    private List<StudentSubjectDto> expectedStudentSubjectDtos;
    private DataTableRequest request;
    private DataTableResponse<SubjectDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", SubjectServiceImpl.class.getSimpleName());
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedSubject = Subject.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .build();
        expectedSubjectDto = SubjectDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .build();
        expectedStudentSubjectDto = StudentSubjectDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .build();
        expectedSubjects = List.of(expectedSubject);
        expectedSubjectDtos = List.of(expectedSubjectDto);
        expectedStudentSubjectDtos = List.of(expectedStudentSubjectDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedSubjectDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", SubjectServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldCreateSubject() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(subjectDao.create(Mockito.any(Subject.class))).thenReturn(expectedSubject);

        boolean actualCreateState = subjectService.create(expectedSubjectDto);

        ArgumentCaptor<Subject> subjectCaptor = ArgumentCaptor.forClass(Subject.class);
        Mockito.verify(subjectDao, Mockito.times(1)).create(subjectCaptor.capture());

        Assertions.assertEquals("name", subjectCaptor.getValue().getName());
        Assertions.assertEquals("description", subjectCaptor.getValue().getDescription());

        Assertions.assertTrue(actualCreateState);
    }

    @Test
    void shouldUpdateSubject() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(subjectDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedSubject));
        Mockito.when(subjectDao.update(Mockito.any(Subject.class))).thenReturn(expectedSubject);

        boolean actualUpdateState = subjectService.update(expectedSubjectDto);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Subject> subjectCaptor = ArgumentCaptor.forClass(Subject.class);
        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(subjectDao, Mockito.times(1)).update(subjectCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedSubject, subjectCaptor.getValue());
        Assertions.assertTrue(actualUpdateState);
    }

    @Test
    void shouldFindSubjectByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(subjectDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedSubject));

        Optional<SubjectDto> actualSubjectDto = subjectService.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(subjectDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedSubjectDto), actualSubjectDto);
    }


    @Test
    void shouldFindAllSubjects() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(subjectDao.findAll()).thenReturn(expectedSubjects);

        List<SubjectDto> actualSubjectDtos = subjectService.findAll();

        Mockito.verify(subjectDao, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedSubjectDtos, actualSubjectDtos);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingSubjectByUuidIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = subjectService.existsByUuid(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfSubjectExistsByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.when(subjectDao.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        boolean actualExistence = subjectService.existsByUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(subjectDao, Mockito.times(1)).existsByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldBeExistedByName() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any());
        Mockito.when(subjectDao.existsByName(Mockito.any(String.class))).thenReturn(true);
        boolean actualExistence = subjectService.existsByName("name");
        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(subjectDao, Mockito.times(1)).existsByName(nameCaptor.capture());
        Assertions.assertEquals("name", nameCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldFindAllSubjectsByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(subjectDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedSubjects);
            Mockito.when(subjectDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<SubjectDto> actualDataTableSubjectResponse = subjectService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(subjectDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(subjectDao, Mockito.times(1)).count(requestCaptor.capture());

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(response, actualDataTableSubjectResponse);
        }
    }

    @Test
    void shouldFindAllSubjectsForStudentByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(subjectDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedSubjects);
            Mockito.when(subjectDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);
            Mockito.when(testService.findAllBySubjectUuidForStudent(Mockito.any(UUID.class))).thenReturn(Collections.emptyList());

            DataTableResponse<StudentSubjectDto> actualDataTableSubjectResponse = subjectService.findAllForStudent(request, uuid);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(subjectDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(subjectDao, Mockito.times(1)).count(requestCaptor.capture());

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(expectedStudentSubjectDtos, actualDataTableSubjectResponse.getDtos());
        }
    }

    @Test
    void shouldDeleteSubjectByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(subjectDao.deleteByUuid(Mockito.any(UUID.class))).thenReturn(true);

        boolean actualDeleteStatus = subjectService.deleteByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(subjectDao, Mockito.times(1)).deleteByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());

        Assertions.assertTrue(actualDeleteStatus);
    }
}
