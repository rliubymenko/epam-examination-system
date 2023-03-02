package com.epam.examinationsystem.core.web.command.impl.student.account;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.MailService;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.web.command.CommandResult;
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

@ExtendWith(MockitoExtension.class)
class ResetStudentPasswordCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(ResetStudentPasswordCommandTest.class);

    @Mock
    private UserService userService;

    @Mock
    private MailService mailService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ResetStudentPasswordCommand resetStudentPasswordCommand;

    private UserDto expectedUserDto;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", ResetStudentPasswordCommand.class.getSimpleName());
        expectedUserDto = UserDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setUsername("username")
                .setEmail("email@email.com")
                .setFirstName("firstname")
                .setLastName("lastname")
                .build();
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", ResetStudentPasswordCommand.class.getSimpleName());
    }

    @Test
    void shouldResetPasswordForUser() throws ServiceException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.anyString())).thenReturn(expectedUserDto);
        Mockito.when(userService.resetPassword(Mockito.any(UserDto.class), Mockito.anyString())).thenReturn(true);
        Mockito.doNothing().when(mailService).sendNewPassword(Mockito.anyString(), Mockito.anyString());

        CommandResult actualResult = resetStudentPasswordCommand.execute(request, response);

        ArgumentCaptor<UserDto> userDtoCaptor = ArgumentCaptor.forClass(UserDto.class);
        ArgumentCaptor<String> mailCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(userService, Mockito.times(1)).resetPassword(userDtoCaptor.capture(), Mockito.anyString());
        Mockito.verify(mailService, Mockito.times(1)).sendNewPassword(mailCaptor.capture(), Mockito.anyString());
        Assertions.assertEquals(expectedUserDto, userDtoCaptor.getValue());
        Assertions.assertEquals("email@email.com", mailCaptor.getValue());
        Assertions.assertEquals("/students/account", actualResult.getPage());
        Assertions.assertTrue(actualResult.isRedirect());
    }
}
