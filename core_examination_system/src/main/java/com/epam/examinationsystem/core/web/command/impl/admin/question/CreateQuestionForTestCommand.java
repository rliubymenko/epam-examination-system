package com.epam.examinationsystem.core.web.command.impl.admin.question;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.web.command.ActionCommand;
import com.epam.examinationsystem.core.web.command.CommandResult;
import com.epam.examinationsystem.core.web.command.constant.Attribute;
import com.epam.examinationsystem.core.web.command.constant.Parameter;
import com.epam.examinationsystem.core.web.command.constant.Path;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@PleaseService
public class CreateQuestionForTestCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CreateQuestionForTestCommand.class);

    @PleaseInject
    private QuestionService questionService;

    @PleaseInject
    private AnswerService answerService;

    @PleaseInject
    private TestService testService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String type = request.getParameter(Parameter.TYPE);
            String content = request.getParameter(Parameter.CONTENT);
            String description = request.getParameter(Parameter.DESCRIPTION);
            String testUuid = request.getParameter(Parameter.TEST_UUID);
            String queryString = request.getQueryString();

            Set<String> inconsistencies = performValidation(content, type, testUuid);

            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid question data");
                List<TestDto> tests = testService.findAll();
                request.setAttribute(Attribute.TESTS, tests);
                if (queryString != null) {
                    request.setAttribute(Attribute.TEST_UUID, testUuid);
                }
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.NEW_QUESTION_PAGE);
            }
            List<AnswerDto> answers = extractAnswers(request, type);

            QuestionDto question = QuestionDto.builder()
                    .setType(type)
                    .setContent(content)
                    .setDescription(description)
                    .setTest(new QuestionDto.TestForQuestion(testUuid, null))
                    .build();

            QuestionDto savedQuestion = questionService.create(question);
            LOG.debug("The question {} has been created successfully", savedQuestion);
            answerService.createAnswersForQuestion(answers, savedQuestion);
            LOG.debug("The answers {} has been created successfully for question {}", answers, savedQuestion);
            if (queryString != null) {
                return new CommandResult(Path.TESTS, true);
            }
            return new CommandResult(Path.QUESTIONS, true);

        } catch (ServiceException e) {
            LOG.error("Error during creating question with answers has been occurred {}", e.getMessage());
            return new CommandResult(Path.NEW_QUESTION_PAGE);
        }
    }

    private Set<String> performValidation(String content, String type, String testUuid) {
        Set<String> inconsistencies = new HashSet<>();
        if (StringUtils.isBlank(content)) {
            inconsistencies.add("content");
        }
        if (type.equals("-1")) {
            inconsistencies.add("type");
        }
        if (testUuid.equals("-1")) {
            inconsistencies.add("test");
        }
        return inconsistencies;
    }

    private List<AnswerDto> extractAnswers(HttpServletRequest request, String type) {
        List<AnswerDto> answers = new ArrayList<>();
        switch (type) {
            case "text" -> {
                String textAnswer = request.getParameter(Parameter.TEXT_ANSWER);
                if (StringUtils.isNotBlank(textAnswer)) {
                    AnswerDto answer = AnswerDto.builder()
                            .setContent(textAnswer)
                            .setIsCorrect(BooleanUtils.TRUE)
                            .build();
                    answers.add(answer);
                }
            }
            case "numerical" -> {
                String numericalAnswer = request.getParameter(Parameter.NUMERICAL_ANSWER);
                if (StringUtils.isNotBlank(numericalAnswer)) {
                    AnswerDto answer = AnswerDto.builder()
                            .setContent(numericalAnswer)
                            .setIsCorrect(BooleanUtils.TRUE)
                            .build();
                    answers.add(answer);
                }
            }
            case "true_false" -> {
                String trueFalseAnswer = request.getParameter(Parameter.TRUE_FALSE_ANSWER);
                String booleanAnswerString = BooleanUtils.toStringTrueFalse(BooleanUtils.toBoolean(trueFalseAnswer));
                AnswerDto answer = AnswerDto.builder()
                        .setContent(booleanAnswerString)
                        .setIsCorrect(BooleanUtils.TRUE)
                        .build();
                answers.add(answer);
            }
            case "single_choice" -> {
                String rightAnswerIndex = request.getParameter(Parameter.ANSWERS_CHOICE);
                String[] singleChoiceAnswers = request.getParameterValues(Parameter.ANSWERS_ANSWER);
                if (ParameterValidator.isNotEmptyArray(singleChoiceAnswers)) {
                    int correctChoiceIndex = Integer.parseInt(rightAnswerIndex) - 1;
                    for (int answerIndex = 0; answerIndex < singleChoiceAnswers.length; answerIndex++) {
                        AnswerDto answer;
                        if (answerIndex == correctChoiceIndex) {
                            answer = AnswerDto.builder()
                                    .setContent(singleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.TRUE)
                                    .build();
                        } else {
                            answer = AnswerDto.builder()
                                    .setContent(singleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.FALSE)
                                    .build();
                        }
                        answers.add(answer);
                    }
                }
            }
            case "multiple_choice" -> {
                String[] rightAnswerIndexes = request.getParameterValues(Parameter.ANSWERS_CHOICE);
                String[] multipleChoiceAnswers = request.getParameterValues(Parameter.ANSWERS_ANSWER);
                if (ParameterValidator.isNotEmptyArray(multipleChoiceAnswers)) {
                    int[] correctChoices = Stream.of(rightAnswerIndexes).mapToInt(Integer::parseInt).toArray();
                    for (int answerIndex = 0; answerIndex < multipleChoiceAnswers.length; answerIndex++) {
                        AnswerDto answer;
                        if (ArrayUtils.contains(correctChoices, answerIndex + 1)) {
                            answer = AnswerDto.builder()
                                    .setContent(multipleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.TRUE)
                                    .build();
                        } else {
                            answer = AnswerDto.builder()
                                    .setContent(multipleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.FALSE)
                                    .build();
                        }
                        answers.add(answer);
                    }
                }
            }
        }
        return answers;
    }
}
