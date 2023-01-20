package com.epam.examinationsystem.core.web.command.impl.student.test;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import com.epam.examinationsystem.core.web.command.constant.SessionConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@PleaseService
public class ExamineStudentCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ExamineStudentCommand.class);

    @PleaseInject
    private TestService testService;

    @PleaseInject
    private UserService userService;

    @PleaseInject
    private UserTestService userTestService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String uuid = request.getParameter(Parameter.UUID);
        try {
            if (ParameterValidator.isValidUUID(uuid) && testService.existsByUuid(UUID.fromString(uuid))) {
                Optional<StudentTestDto> maybeTest = testService.findByUuidForTesting(UUID.fromString(uuid));
                if (maybeTest.isPresent()) {

                    StudentTestDto test = maybeTest.get();
                    UserDto currentUser = (UserDto) request.getSession().getAttribute(SessionConstant.CURRENT_USER);
                    String startTime = request.getParameter(Parameter.START_TIME);
                    String endTime = request.getParameter(Parameter.END_TIME);
                    float percentageOfCorrectAnswers = calculateFinalMarkValue(request.getParameterMap(), test);

                    UserTestDto userTestDto = UserTestDto.builder()
                            .setTest(new UserTestDto.TestAdjacent(test.getUuid(), test.getName()))
                            .setUser(new UserTestDto.UserAdjacent(currentUser.getUuid(), currentUser.getUsername()))
                            .setStartTime(startTime)
                            .setEndTime(endTime)
                            .setMarkValue(String.valueOf(percentageOfCorrectAnswers))
                            .build();

                    if (userTestService.createAfterExam(userTestDto)) {
                        LOG.debug("The user test {} has been created successfully", userTestDto);
                        return new CommandResult(Path.TESTS_FOR_STUDENT, true);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error during examining student and calculating the result mark has been occurred {}", e.getMessage());
            return new CommandResult(Path.TESTS_FOR_STUDENT, true);
        }
        return new CommandResult(Path.HOME, true);
    }

    private float calculateFinalMarkValue(Map<String, String[]> parameters, StudentTestDto test) {
        Integer correctAnswersAmount = 0;
        Integer totalQuestionNumber = test.getQuestions().size();
        for (StudentTestDto.QuestionForStudentTestDto question : test.getQuestions()) {

            switch (question.getType()) {
                case "text", "numerical" -> {
                    for (StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto answer : question.getAnswers()) {
                        String answerKey = "answer_uuid" + answer.getUuid();
                        String[] answers = parameters.get(answerKey);
                        if (ArrayUtils.isNotEmpty(answers) && Boolean.parseBoolean(answer.getIsCorrect()) && answer.getContent().equals(answers[0])) {
                            correctAnswersAmount += 1;
                        }
                    }
                }
                case "true_false" -> {
                    for (StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto answer : question.getAnswers()) {
                        String answerKey = "answer_uuid" + answer.getUuid();
                        String[] answers = parameters.get(answerKey);
                        String correctness = BooleanUtils.toStringTrueFalse(BooleanUtils.toBoolean(answers != null ? answers[0] : null));
                        if (correctness.equals(answer.getContent())) {
                            correctAnswersAmount += 1;
                        }
                    }
                }
                case "single_choice", "multiple_choice" -> {
                    String answerKey = "choice_question_uuid" + question.getUuid();
                    String[] answers = parameters.get(answerKey);
                    if (ArrayUtils.isNotEmpty(answers)) {
                        Set<String> currentAnswerSet = new HashSet<>(Arrays.asList(answers));
                        Set<String> allCorrectAnswers = getAllCorrectAnswerUuids(question.getAnswers());
                        if (SetUtils.disjunction(currentAnswerSet, allCorrectAnswers).isEmpty()) {
                            correctAnswersAmount += 1;
                        }
                    }
                }
            }
        }
        return correctAnswersAmount.floatValue() * 100 / totalQuestionNumber.floatValue();
    }

    private Set<String> getAllCorrectAnswerUuids(List<StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto> answers) {
        return answers
                .stream()
                .filter(answer -> Boolean.parseBoolean(answer.getIsCorrect()))
                .map(StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto::uuid)
                .collect(Collectors.toSet());
    }
}
