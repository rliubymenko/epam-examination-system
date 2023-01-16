package com.epam.examinationsystem.core.web.command.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
import com.epam.examinationsystem.core.service.QuestionService;
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

import java.util.*;
import java.util.stream.Stream;

@PleaseService
public class CreateAnswerCommand implements ActionCommand {

    private static final Logger LOG = LoggerFactory.getLogger(CreateAnswerCommand.class);

    @PleaseInject
    private AnswerService answerService;

    @PleaseInject
    private QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String questionUuid = request.getParameter(Parameter.QUESTION_UUID);

            Set<String> inconsistencies = performValidation(questionUuid);

            if (CollectionUtils.isNotEmpty(inconsistencies)) {
                LOG.error("Invalid answer data");
                request.setAttribute(Attribute.INCONSISTENCIES, inconsistencies);
                return new CommandResult(Path.NEW_ANSWER_PAGE);
            }

            QuestionDto question = questionService.findByUuid(UUID.fromString(questionUuid)).get();
            List<AnswerDto> answers = extractAnswers(request, question);
            answerService.createAnswersForQuestion(answers, question);
            LOG.debug("The answers {} has been created successfully for question {}", answers, question);
            return new CommandResult(Path.ANSWERS, true);
        } catch (ServiceException e) {
            LOG.error("Error during creating answers has been occurred {}", e.getMessage());
            return new CommandResult(Path.NEW_ANSWER_PAGE);
        }
    }

    private Set<String> performValidation(String questionUuid) {
        Set<String> inconsistencies = new HashSet<>();
        if (questionUuid.equals("-1")) {
            inconsistencies.add("question");
        }
        return inconsistencies;
    }

    private List<AnswerDto> extractAnswers(HttpServletRequest request, QuestionDto question) {
        List<AnswerDto> answers = new ArrayList<>();
        AnswerDto.QuestionForAnswer questionForAnswer = new AnswerDto.QuestionForAnswer(
                question.getUuid(),
                question.getContent(),
                question.getType()
        );
        switch (question.getType()) {
            case "text" -> {
                String textAnswer = request.getParameter(Parameter.TEXT_ANSWER);
                if (StringUtils.isNotBlank(textAnswer)) {
                    AnswerDto answer = AnswerDto.builder()
                            .setContent(textAnswer)
                            .setIsCorrect(BooleanUtils.TRUE)
                            .setQuestion(questionForAnswer)
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
                            .setQuestion(questionForAnswer)
                            .build();
                    answers.add(answer);
                }
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
                                    .setQuestion(questionForAnswer)
                                    .build();
                        } else {
                            answer = AnswerDto.builder()
                                    .setContent(singleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.FALSE)
                                    .setQuestion(questionForAnswer)
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
                                    .setQuestion(questionForAnswer)
                                    .build();
                        } else {
                            answer = AnswerDto.builder()
                                    .setContent(multipleChoiceAnswers[answerIndex])
                                    .setIsCorrect(BooleanUtils.FALSE)
                                    .setQuestion(questionForAnswer)
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
