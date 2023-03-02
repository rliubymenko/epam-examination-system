package com.epam.examinationsystem.core.web.command.impl.student.account;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class EditStudentCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(EditStudentCommandTest.class);

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private EditStudentCommand editStudentCommand;

    private String uuid;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private UserDto expectedUserDto;
    Set<String> expectedInconsistencies;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", EditStudentCommand.class.getSimpleName());
        uuid = "00000000-0000-0000-0000-000000000001";
        username = "username";
        email = "email@email.com";
        firstName = "firstname";
        lastName = "lastname";
        expectedUserDto = UserDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setUsername("username")
                .setEmail("email@email.com")
                .setFirstName("firstname")
                .setLastName("lastname")
                .build();
        expectedInconsistencies = Set.of(
                "username",
                "used_username",
                "email",
                "used_email",
                "firstName",
                "lastName"
        );
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", EditStudentCommand.class.getSimpleName());
    }

    @Test
    void shouldSuccessfullyUpdateUserAndReturnRedirectToStudentAccountPage() throws ServiceException {
        Mockito.when(request.getParameter(Parameter.UUID)).thenReturn(uuid);
        Mockito.when(userService.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        Mockito.when(userService.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserDto));
        Mockito.when(request.getParameter(Parameter.USERNAME)).thenReturn(username);
        Mockito.when(request.getParameter(Parameter.EMAIL)).thenReturn(email);
        Mockito.when(request.getParameter(Parameter.FIRST_NAME)).thenReturn(firstName);
        Mockito.when(request.getParameter(Parameter.LAST_NAME)).thenReturn(lastName);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(userService.update(Mockito.any(UserDto.class), Mockito.anyBoolean())).thenReturn(true);

        CommandResult actualResult = editStudentCommand.execute(request, response);

        ArgumentCaptor<UserDto> userCaptor = ArgumentCaptor.forClass(UserDto.class);
        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Boolean> isLoggedUserCaptor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(userService, Mockito.times(1)).update(userCaptor.capture(), isLoggedUserCaptor.capture());
        Mockito.verify(userService, Mockito.times(2)).findByUuid(uuidCaptor.capture());
        Mockito.verify(session, Mockito.times(1)).setAttribute(SessionConstant.CURRENT_USER, expectedUserDto);
        Assertions.assertEquals(expectedUserDto, userCaptor.getValue());
        Assertions.assertTrue(isLoggedUserCaptor.getValue());
        Assertions.assertEquals(UUID.fromString(uuid), uuidCaptor.getAllValues().get(0));
        Assertions.assertEquals(UUID.fromString(uuid), uuidCaptor.getAllValues().get(1));
        Assertions.assertEquals("/students/account", actualResult.getPage());
        Assertions.assertTrue(actualResult.isRedirect());
    }

    @Test
    void shouldFailValidationAndReturnSetWithInconsistencies() throws ServiceException {
        Mockito.when(request.getParameter(Parameter.UUID)).thenReturn(uuid);
        Mockito.when(userService.existsByUuid(Mockito.any(UUID.class))).thenReturn(true);
        Mockito.when(userService.existsByUsername(Mockito.any(String.class))).thenReturn(true);
        Mockito.when(userService.existsByEmail(Mockito.any(String.class))).thenReturn(true);
        Mockito.when(userService.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserDto));
        Mockito.when(request.getParameter(Parameter.USERNAME)).thenReturn("");
        Mockito.when(request.getParameter(Parameter.EMAIL)).thenReturn("");
        Mockito.when(request.getParameter(Parameter.FIRST_NAME)).thenReturn("");
        Mockito.when(request.getParameter(Parameter.LAST_NAME)).thenReturn("");
        Mockito.when(request.getParameter(Parameter.LAST_NAME)).thenReturn("");

        CommandResult actualResult = editStudentCommand.execute(request, response);

        ArgumentCaptor<UserDto> userCaptor = ArgumentCaptor.forClass(UserDto.class);
        ArgumentCaptor<?> inconsistenciesCaptor = ArgumentCaptor.forClass(Set.class);
        Mockito.verify(request, Mockito.times(1)).setAttribute(Mockito.anyString(), userCaptor.capture());
        Mockito.verify(request, Mockito.times(1)).setAttribute(Mockito.anyString(), inconsistenciesCaptor.capture());

        Assertions.assertEquals(expectedUserDto, userCaptor.getValue());
        Assertions.assertEquals(expectedInconsistencies, inconsistenciesCaptor.getValue());
        Assertions.assertEquals("/view/student/editStudent.jsp", actualResult.getPage());
        Assertions.assertFalse(actualResult.isRedirect());
    }
}
