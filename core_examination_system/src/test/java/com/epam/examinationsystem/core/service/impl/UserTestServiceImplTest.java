package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.enumeration.UserType;
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
class UserTestServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestServiceImplTest.class);

    @Mock
    private UserTestDao userTestDao;

    @Mock
    private TestDao testDao;

    @Mock
    private UserDao userDao;

    @Mock
    private TransactionManager<UserTest> transactionManager;

    @InjectMocks
    private UserTestServiceImpl userTestService;

    private UUID uuid;

    private Role expectedRole;
    private User expectedUser;
    private com.epam.examinationsystem.core.entity.Test expectedTest;
    private UserTest expectedUserTest;
    private UserTestDto expectedUserTestDto;
    private List<UserTest> expectedUserTests;
    private List<UserTestDto> expectedUserTestDtos;
    private DataTableRequest request;
    private DataTableResponse<UserTestDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", UserTestServiceImpl.class.getSimpleName());
        uuid = UUID.fromString("00000000-0000-0000-0000-000000000001");
        expectedRole = Role.builder()
                .setUuid(uuid)
                .setName(UserType.ADMIN)
                .build();
        expectedUser = User.builder()
                .setUuid(uuid)
                .setUsername("username")
                .setEmail("email@dsf.sdf")
                .setPassword("dsfdsfgsdgfdfghfdgh")
                .setFirstName("firstname")
                .setLastName("lastname")
                .setIsActivated(true)
                .setRole(expectedRole)
                .build();
        expectedTest = com.epam.examinationsystem.core.entity.Test.builder()
                .setUuid(uuid)
                .setName("name")
                .setDescription("description")
                .setComplexity(TestComplexity.EASY)
                .setDuration(100)
                .setTotalAttemptNumber(5)
                .setExpirationDate(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setCreated(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setMaxAttemptNumber(55)
                .build();
        expectedUserTest = UserTest.builder()
                .setUuid(uuid)
                .setIsSelected(true)
                .setIsCompleted(true)
                .setMarkValue(100F)
                .setAttemptNumber(1)
                .setStartTime(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setEndTime(LocalDateTime.parse("2015-08-04T10:11:30"))
                .setUser(expectedUser)
                .setTest(expectedTest)
                .build();
        expectedUserTestDto = UserTestDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setIsSelected("true")
                .setIsCompleted("true")
                .setMarkValue("100")
                .setAttemptNumber("1")
                .setStartTime("2015-08-04T10:11:30")
                .setEndTime("2015-08-04T10:11:30")
                .setUser(new UserTestDto.UserAdjacent("00000000-0000-0000-0000-000000000001", "username"))
                .setTest(new UserTestDto.TestAdjacent("00000000-0000-0000-0000-000000000001", "name"))
                .build();
        expectedUserTests = List.of(expectedUserTest);
        expectedUserTestDtos = List.of(expectedUserTestDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedUserTestDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", UserTestServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldCreateUserTest() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.createAndSelect(Mockito.any(UserTest.class))).thenReturn(expectedUserTest);
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));

        UserTestDto actualUserTestDto = userTestService.create(expectedUserTestDto);

        ArgumentCaptor<UUID> userUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UserTest> userTestCaptor = ArgumentCaptor.forClass(UserTest.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUuid(userUuidCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).createAndSelect(userTestCaptor.capture());

        Assertions.assertEquals(uuid, userUuidCaptor.getValue());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());
        Assertions.assertEquals(expectedUser, userTestCaptor.getValue().getUser());
        Assertions.assertEquals(expectedTest, userTestCaptor.getValue().getTest());
        Assertions.assertEquals(expectedUserTestDto, actualUserTestDto);
    }

    @Test
    void shouldThrowServiceExceptionWhenCreatingUserTest() throws DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).rollback();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userDao.findByUuid(uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.create(expectedUserTestDto);
        });
    }

    @Test
    void shouldUpdateUserTestAfterExam() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserTest));
        Mockito.when(testDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedTest));
        Mockito.when(testDao.incrementTotalAttemptNumber(Mockito.any(com.epam.examinationsystem.core.entity.Test.class))).thenReturn(true);
        Mockito.when(userTestDao.update(Mockito.any(UserTest.class))).thenReturn(expectedUserTest);

        boolean actualUserTestUpdateState = userTestService.updateAfterExam(expectedUserTestDto);

        ArgumentCaptor<UUID> userTestUuid1Captor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> userTestUuid2Captor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UUID> testUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UserTest> userTestCaptor = ArgumentCaptor.forClass(UserTest.class);
        ArgumentCaptor<com.epam.examinationsystem.core.entity.Test> testCaptor = ArgumentCaptor.forClass(com.epam.examinationsystem.core.entity.Test.class);
        Mockito.verify(userTestDao, Mockito.times(1)).findByUserAndTestUuid(userTestUuid1Captor.capture(), userTestUuid2Captor.capture());
        Mockito.verify(testDao, Mockito.times(1)).findByUuid(testUuidCaptor.capture());
        Mockito.verify(userTestDao, Mockito.times(1)).update(userTestCaptor.capture());
        Mockito.verify(testDao, Mockito.times(1)).incrementTotalAttemptNumber(testCaptor.capture());

        Assertions.assertEquals(uuid, userTestUuid1Captor.getValue());
        Assertions.assertEquals(uuid, userTestUuid2Captor.getValue());
        Assertions.assertEquals(uuid, testUuidCaptor.getValue());
        Assertions.assertEquals(2, userTestCaptor.getValue().getAttemptNumber());
        Assertions.assertEquals(true, userTestCaptor.getValue().getIsSelected());
        Assertions.assertEquals(true, userTestCaptor.getValue().getIsCompleted());
        Assertions.assertEquals(expectedTest, testCaptor.getValue());
        Assertions.assertTrue(actualUserTestUpdateState);
    }

    @Test
    void shouldThrowServiceExceptionWhenUpdatingUserTest() throws DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).rollback();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findByUserAndTestUuid(uuid, uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.updateAfterExam(expectedUserTestDto);
        });
    }

    @Test
    void shouldSetStartTimeForUserTest() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(userTestDao).setStartTime(Mockito.any(UUID.class), Mockito.any(LocalDateTime.class));
        userTestService.setStartTime(uuid, LocalDateTime.parse("2015-08-04T10:11:30"));
        ArgumentCaptor<UUID> userTestUuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<LocalDateTime> startTimeCaptor = ArgumentCaptor.forClass(LocalDateTime.class);
        Mockito.verify(userTestDao, Mockito.times(1)).setStartTime(userTestUuidCaptor.capture(), startTimeCaptor.capture());
        Assertions.assertEquals(uuid, userTestUuidCaptor.getValue());
        Assertions.assertEquals(LocalDateTime.parse("2015-08-04T10:11:30"), startTimeCaptor.getValue());
    }

    @Test
    void shouldThrowServiceExceptionWhenSettingStartTime() throws DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).rollback();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.doThrow(DaoException.class).when(userTestDao).setStartTime(Mockito.any(UUID.class), Mockito.any(LocalDateTime.class));
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.setStartTime(uuid, LocalDateTime.parse("2015-08-04T10:11:30"));
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.setStartTime(uuid, LocalDateTime.parse("2015-08-04T10:11:30"));
        });
    }

    @Test
    void shouldFindUserTestByUserUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserUuid(Mockito.any(UUID.class))).thenReturn(expectedUserTests);
        List<UserTestDto> actualUserTestDtos = userTestService.findByUserUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userTestDao, Mockito.times(1)).findByUserUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedUserTestDtos, actualUserTestDtos);
    }

    @Test
    void shouldThrowServiceExceptionWhenSearchingUserTestByUuid() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserUuid(Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findByUserUuid(uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.findByUserUuid(uuid);
        });
    }

    @Test
    void shouldFindUserTestByUserAndTestUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserTest));
        Optional<UserTestDto> actualUserTestDto = userTestService.findByUserAndTestUuid(uuid, uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userTestDao, Mockito.times(1)).findByUserAndTestUuid(uuidCaptor.capture(), uuidCaptor.capture());
        List<UUID> uuids = uuidCaptor.getAllValues();
        Assertions.assertEquals(uuid, uuids.get(0));
        Assertions.assertEquals(uuid, uuids.get(1));
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUserTestDto), actualUserTestDto);
    }

    @Test
    void shouldThrowServiceExceptionWhenSearchingUserTestByUserAndTestUuid() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findByUserAndTestUuid(uuid, uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.findByUserAndTestUuid(uuid, uuid);
        });
    }

    @Test
    void shouldFindUserTestByUserAndTestUuidAndGetCurrentAttemptNumber() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserTest));
        int actualCurrentAttemptNumber = userTestService.getCurrentAttemptNumber(uuid, uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userTestDao, Mockito.times(1)).findByUserAndTestUuid(uuidCaptor.capture(), uuidCaptor.capture());
        List<UUID> uuids = uuidCaptor.getAllValues();
        Assertions.assertEquals(uuid, uuids.get(0));
        Assertions.assertEquals(uuid, uuids.get(1));
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(1, actualCurrentAttemptNumber);
    }

    @Test
    void shouldThrowServiceExceptionWhenGettingCurrentTimeByUserAndTestUuid() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findByUserAndTestUuid(uuid, uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.getCurrentAttemptNumber(uuid, uuid);
        });
    }

    @Test
    void shouldFindUserTestByUserAndTestUuidAndGetIsSelected() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserTest));
        boolean actualIsSelected = userTestService.isSelected(uuid, uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userTestDao, Mockito.times(1)).findByUserAndTestUuid(uuidCaptor.capture(), uuidCaptor.capture());
        List<UUID> uuids = uuidCaptor.getAllValues();
        Assertions.assertEquals(uuid, uuids.get(0));
        Assertions.assertEquals(uuid, uuids.get(1));
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualIsSelected);
    }

    @Test
    void shouldThrowServiceExceptionWhenGettingIsSelectedByUserAndTestUuid() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findByUserAndTestUuid(Mockito.any(UUID.class), Mockito.any(UUID.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findByUserAndTestUuid(uuid, uuid);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.isSelected(uuid, uuid);
        });
    }

    @Test
    void shouldFindAllUserTestsByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(userTestDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedUserTests);
            Mockito.when(userTestDao.findAll()).thenReturn(Collections.emptyList());
            Mockito.when(userTestDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<UserTestDto> actualDataTableUserTestResponse = userTestService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(userTestDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(userTestDao, Mockito.times(1)).count(requestCaptor.capture());
            Mockito.verify(userTestDao, Mockito.times(1)).findAll();

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(response, actualDataTableUserTestResponse);
        }
    }

    @Test
    void shouldThrowServiceExceptionWhenSearchingAllUserTestsByRequestParams() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findAll(Mockito.any(DataTableRequest.class))).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findAll(request);
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.findAll(request);
        });
    }

    @Test
    void shouldFindAllUserTests() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findAll()).thenReturn(expectedUserTests);
        List<UserTestDto> actualUserTests = userTestService.findAll();
        Mockito.verify(userTestDao, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedUserTestDtos, actualUserTests);
    }

    @Test
    void shouldThrowServiceExceptionWhenSearchingAllUserTests() throws DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userTestDao.findAll()).thenThrow(DaoException.class);
        Assertions.assertThrows(DaoException.class, () -> {
            userTestDao.findAll();
        });
        Assertions.assertThrows(ServiceException.class, () -> {
            userTestService.findAll();
        });
    }
}
