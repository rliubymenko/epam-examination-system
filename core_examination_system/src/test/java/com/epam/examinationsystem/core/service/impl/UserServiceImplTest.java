package com.epam.examinationsystem.core.service.impl;

import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.entity.User;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private TransactionManager<User> transactionManager;

    @InjectMocks
    private UserServiceImpl userService;

    private UUID uuid;
    private Role expectedRole;
    private User expectedUser;
    private UserDto expectedUserDto;
    private List<User> expectedUsers;
    private List<UserDto> expectedUserDtos;
    private DataTableRequest request;
    private DataTableResponse<UserDto> response;

    @BeforeEach
    void setUp() {
        LOG.info("Start tests for {}", UserServiceImpl.class.getSimpleName());
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
        expectedUserDto = UserDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setUsername("username")
                .setEmail("email@dsf.sdf")
                .setFirstName("firstname")
                .setLastName("lastname")
                .setRole("admin")
                .setIsActivated(true)
                .build();
        expectedUsers = List.of(expectedUser);
        expectedUserDtos = List.of(expectedUserDto);
        request = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        response = new DataTableResponse<>();
        response.setDtos(expectedUserDtos);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", UserServiceImpl.class.getSimpleName());
    }

    @Test
    void shouldFindUserByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));

        Optional<UserDto> actualUserDto = userService.findByUuid(uuid);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUserDto), actualUserDto);
    }

    @Test
    void shouldFindUserByUsername() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findByUsername(Mockito.any(String.class))).thenReturn(Optional.of(expectedUser));

        Optional<UserDto> actualUserDto = userService.findByUsername("username");

        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(usernameCaptor.capture());
        Assertions.assertEquals("username", usernameCaptor.getValue());
        Assertions.assertEquals(Optional.of(expectedUserDto), actualUserDto);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingUserByUuidIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = userService.existsByUuid(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfUserExistsByUuid() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.when(userDao.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        boolean actualExistence = userService.existsByUuid(uuid);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userDao, Mockito.times(1)).existsByUuid(uuidCaptor.capture());
        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingUserByUsernameIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = userService.existsByUsername(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfUserExistsByUsername() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.when(userDao.findByUsername(Mockito.any(String.class))).thenReturn(Optional.of(expectedUser));
        boolean actualExistence = userService.existsByUsername("username");
        ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUsername(usernameCaptor.capture());
        Assertions.assertEquals("username", usernameCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldReturnEmptyOptionalForSearchingUserByEmailIfIncomingUuidIsNull() throws ServiceException {
        boolean actualExistence = userService.existsByEmail(null);
        Assertions.assertFalse(actualExistence);
    }

    @Test
    void shouldReturnTrueWhileCheckingIfUserExistsByEmail() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.when(userDao.findByEmail(Mockito.any(String.class))).thenReturn(Optional.of(expectedUser));
        boolean actualExistence = userService.existsByEmail("email@dsf.sdf");
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userDao, Mockito.times(1)).findByEmail(emailCaptor.capture());
        Assertions.assertEquals("email@dsf.sdf", emailCaptor.getValue());
        Assertions.assertTrue(actualExistence);
    }

    @Test
    void shouldFindAllUsersParams() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findAll()).thenReturn(expectedUsers);
        List<UserDto> actualDataTableUserResponse = userService.findAll();
        Mockito.verify(userDao, Mockito.times(1)).findAll();
        Assertions.assertEquals(expectedUserDtos, actualDataTableUserResponse);
    }

    @Test
    void shouldFindAllUsersByRequestParams() throws ServiceException, DaoException {
        try (MockedStatic<PageableUtil> pageableUtil = Mockito.mockStatic(PageableUtil.class)) {
            Mockito.doNothing().when(transactionManager).beginWithAutoCommit(Mockito.any(), Mockito.any());
            Mockito.doNothing().when(transactionManager).end();
            Mockito.when(userDao.findAll(Mockito.any(DataTableRequest.class))).thenReturn(expectedUsers);
            Mockito.when(userDao.count(Mockito.any(DataTableRequest.class))).thenReturn(1L);
            pageableUtil.when(() -> PageableUtil.calculatePageableData(request, 1L)).thenReturn(response);

            DataTableResponse<UserDto> actualDataTableUserResponse = userService.findAll(request);

            ArgumentCaptor<DataTableRequest> requestCaptor = ArgumentCaptor.forClass(DataTableRequest.class);
            Mockito.verify(userDao, Mockito.times(1)).findAll(requestCaptor.capture());
            Mockito.verify(userDao, Mockito.times(1)).count(requestCaptor.capture());

            Assertions.assertEquals(request, requestCaptor.getValue());
            Assertions.assertEquals(response, actualDataTableUserResponse);
        }
    }

    @Test
    void shouldCreateStudent() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.create(Mockito.any(User.class))).thenReturn(expectedUser);
        Mockito.when(roleDao.findByUserType(Mockito.any(UserType.class))).thenReturn(Optional.of(expectedRole));

        boolean actualCreateState = userService.createStudent(expectedUserDto);

        ArgumentCaptor<UserType> userTypeCaptor = ArgumentCaptor.forClass(UserType.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(roleDao, Mockito.times(1)).findByUserType(userTypeCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).create(userCaptor.capture());

        Assertions.assertEquals(UserType.STUDENT, userTypeCaptor.getValue());
        Assertions.assertEquals("username", userCaptor.getValue().getUsername());
        Assertions.assertEquals("email@dsf.sdf", userCaptor.getValue().getEmail());
        Assertions.assertEquals("firstname", userCaptor.getValue().getFirstName());
        Assertions.assertEquals("lastname", userCaptor.getValue().getLastName());
        Assertions.assertTrue(userCaptor.getValue().getIsActivated());
        Assertions.assertEquals(expectedRole, userCaptor.getValue().getRole());
        Assertions.assertTrue(actualCreateState);
    }

    @Test
    void shouldResetPassword() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.resetPassword(Mockito.any(UUID.class), Mockito.any(String.class))).thenReturn(true);

        boolean actualCreateState = userService.resetPassword(expectedUserDto, "newPassword");

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userDao, Mockito.times(1)).resetPassword(uuidCaptor.capture(), Mockito.any());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertTrue(actualCreateState);
    }

    @Test
    void shouldUpdateUserWithoutChangingActivation() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(userDao.updateWithoutChangingActivation(Mockito.any(User.class))).thenReturn(true);

        boolean actualUpdateState = userService.update(expectedUserDto, true);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).updateWithoutChangingActivation(userCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedUser, userCaptor.getValue());
        Assertions.assertTrue(actualUpdateState);
    }

    @Test
    void shouldUpdateUserWithChangingActivation() throws ServiceException, DaoException {
        Mockito.doNothing().when(transactionManager).begin(Mockito.any(), Mockito.any());
        Mockito.doNothing().when(transactionManager).commit();
        Mockito.doNothing().when(transactionManager).end();
        Mockito.when(userDao.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUser));
        Mockito.when(userDao.update(Mockito.any(User.class))).thenReturn(expectedUser);

        boolean actualUpdateState = userService.update(expectedUserDto, false);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userDao, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Mockito.verify(userDao, Mockito.times(1)).update(userCaptor.capture());

        Assertions.assertEquals(uuid, uuidCaptor.getValue());
        Assertions.assertEquals(expectedUser, userCaptor.getValue());
        Assertions.assertTrue(actualUpdateState);
    }
}
