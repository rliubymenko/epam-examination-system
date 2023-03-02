package com.epam.examinationsystem.core.web.command.impl.student.subject;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentSubjectDto;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.web.command.CommandResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class GetAllSubjectsForStudentByParametersCommandTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetAllSubjectsForStudentByParametersCommandTest.class);

    @Mock
    private SubjectService subjectService;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private GetAllSubjectsForStudentByParametersCommand getAllSubjectsForStudentByParametersCommand;

    private UserDto expectedUserDto;
    private StudentSubjectDto expectedStudentSubjectDto;
    private DataTableRequest dataTableRequest;
    private DataTableResponse<StudentSubjectDto> dataTableResponse;

    @BeforeEach
    public void setUp() {
        LOG.info("Start tests for {}", GetAllSubjectsForStudentByParametersCommand.class.getSimpleName());
        expectedUserDto = UserDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setUsername("username")
                .setEmail("email@email.com")
                .setFirstName("firstname")
                .setLastName("lastname")
                .build();
        expectedStudentSubjectDto = StudentSubjectDto.builder()
                .setUuid("00000000-0000-0000-0000-000000000001")
                .setName("name")
                .setDescription("description")
                .build();
        dataTableRequest = new DataTableRequest(1, 10, "created", "desc", "-1", "");
        dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setDtos(List.of(expectedStudentSubjectDto));
    }

    @AfterEach
    void tearDown() {
        LOG.info("Completion of testing {}", GetAllSubjectsForStudentByParametersCommand.class.getSimpleName());
    }

    @Test
    void shouldGetAllSubjectsForStudentByParameters() throws ServiceException {
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute(Mockito.anyString())).thenReturn(expectedUserDto);
        Mockito.when(subjectService.findAllForStudent(Mockito.any(DataTableRequest.class), Mockito.any(UUID.class))).thenReturn(dataTableResponse);
        CommandResult actualResult = getAllSubjectsForStudentByParametersCommand.execute(request, response);
        Assertions.assertEquals("/view/student/subjects.jsp", actualResult.getPage());
        Assertions.assertFalse(actualResult.isRedirect());
    }
}
