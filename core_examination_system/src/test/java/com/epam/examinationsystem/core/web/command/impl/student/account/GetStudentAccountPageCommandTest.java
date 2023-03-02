package com.epam.examinationsystem.core.web.command.impl.student.account;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserTestService;
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

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class GetStudentAccountPageCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetStudentAccountPageCommandTest.class);

    @Mock
    private UserTestService userTestService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private GetStudentAccountPageCommand getEditStudentPageCommand;

    private String uuid;
    private UserDto expectedUserDto;
    private UserTestDto expectedUserTestDto;
    private List<UserTestDto> expectedUserTestDtos;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", GetStudentAccountPageCommand.class.getSimpleName());
        uuid = "00000000-0000-0000-0000-000000000001";
        expectedUserDto = UserDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setUsername("username")
                .setEmail("email@email.com")
                .setFirstName("firstname")
                .setLastName("lastname")
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
        expectedUserTestDtos = List.of(expectedUserTestDto);
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", GetStudentAccountPageCommand.class.getSimpleName());
    }

    @Test
    void shouldSuccessfullyGetStudentEditPage() throws ServiceException {
        Mockito.when(userTestService.findByUserUuid(Mockito.any(UUID.class))).thenReturn(expectedUserTestDtos);
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.anyString())).thenReturn(expectedUserDto);

        CommandResult actualResult = getEditStudentPageCommand.execute(request, response);

        ArgumentCaptor<UUID> uuidCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(userTestService, Mockito.times(1)).findByUserUuid(uuidCaptor.capture());
        Assertions.assertEquals(UUID.fromString(uuid), uuidCaptor.getValue());
        Assertions.assertEquals("/view/student/account.jsp", actualResult.getPage());
        Assertions.assertFalse(actualResult.isRedirect());
    }
}
