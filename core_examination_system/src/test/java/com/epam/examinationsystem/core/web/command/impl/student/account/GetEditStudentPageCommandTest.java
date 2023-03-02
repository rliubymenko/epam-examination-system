package com.epam.examinationsystem.core.web.command.impl.student.account;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class GetEditStudentPageCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetEditStudentPageCommandTest.class);

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GetEditStudentPageCommand getEditStudentPageCommand;

    private String uuid;
    private UserDto expectedUserDto;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", GetEditStudentPageCommand.class.getSimpleName());
        uuid = "00000000-0000-0000-0000-000000000001";
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
        LOG.info("Completion of testing {}", GetEditStudentPageCommand.class.getSimpleName());
    }

    @Test
    void shouldSuccessfullyGetStudentEditPage() throws ServiceException {
        Mockito.when(request.getParameter(Parameter.UUID)).thenReturn(uuid);
        Mockito.when(userService.findByUuid(Mockito.any(UUID.class))).thenReturn(Optional.of(expectedUserDto));

        CommandResult actualResult = getEditStudentPageCommand.execute(request, response);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userService, Mockito.times(1)).findByUuid(uuidCaptor.capture());
        Assertions.assertEquals(UUID.fromString(uuid), uuidCaptor.getValue());
        Assertions.assertEquals("/view/student/editStudent.jsp", actualResult.getPage());
        Assertions.assertFalse(actualResult.isRedirect());
    }

    @Test
    void shouldFailUuidValidationAndReturnHomePage() {
        Mockito.when(request.getParameter(Parameter.UUID)).thenReturn("");
        CommandResult actualResult = getEditStudentPageCommand.execute(request, response);
        Assertions.assertEquals("/home", actualResult.getPage());
        Assertions.assertTrue(actualResult.isRedirect());
    }
}
