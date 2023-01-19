package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @PleaseInject
    private QuestionDao questionDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private AnswerDao answerDao;

    @PleaseInject
    private TransactionManager<Question> transactionManager;

    @Override
    public QuestionDto create(QuestionDto questionDto) throws ServiceException {
        LOG.debug("Creating question by dto {}", questionDto);
        transactionManager.begin(questionDao, testDao, subjectDao);
        try {
            QuestionDto result = null;
            Optional<Test> maybeTest = testDao.findByUuid(UUID.fromString(questionDto.getTest().getUuid()));
            if (maybeTest.isPresent()) {
                Question question = Question.builder()
                        .setContent(questionDto.getContent())
                        .setDescription(questionDto.getDescription())
                        .setType(EnumUtils.getEnumIgnoreCase(QuestionType.class, questionDto.getType()))
                        .setTest(maybeTest.get())
                        .build();
                Question createdQuestion = questionDao.create(question);
                result = QuestionDto.builder().fromQuestion(createdQuestion);
            }
            transactionManager.commit();
            return result;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean update(QuestionDto questionDto) throws ServiceException {
        LOG.debug("Updating question by dto {}", questionDto);
        transactionManager.begin(questionDao, testDao, subjectDao);
        try {
            boolean isUpdated = false;
            UUID uuid = UUID.fromString(questionDto.getUuid());
            Optional<Question> maybeQuestion = questionDao.findByUuid(uuid);
            if (maybeQuestion.isPresent()) {
                Question currentQuestion = maybeQuestion.get();
                Question question = Question.builder()
                        .setUuid(uuid)
                        .setType(currentQuestion.getType())
                        .setContent(questionDto.getContent())
                        .setDescription(questionDto.getDescription())
                        .build();
                questionDao.update(question);
                isUpdated = true;
            }
            transactionManager.commit();
            return isUpdated;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public Optional<QuestionDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find question by uuid {}", uuid);
        if (uuid == null) {
            return Optional.empty();
        }
        transactionManager.begin(questionDao, testDao, subjectDao);
        try {
            Optional<Question> maybeQuestion = questionDao.findByUuid(uuid);
            return maybeQuestion.map(QuestionDto.builder()::fromQuestion);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean existsByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Check if exists by uuid {}", uuid);
        if (uuid == null) {
            return false;
        }
        transactionManager.begin(questionDao);
        try {
            return questionDao.existsByUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public DataTableResponse<QuestionDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all questions by {}", request);
        transactionManager.beginWithAutoCommit(questionDao, testDao, answerDao);
        try {
            List<Question> questions = questionDao.findAll();
            DataTableResponse<QuestionDto> response = PageableUtil.calculatePageableData(request, testDao);
            List<QuestionDto> questionDtos = questions.stream()
                    .map(QuestionDto.builder()::fromQuestion)
                    .toList();
            response.setDtos(questionDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public List<StudentTestDto.QuestionForStudentTestDto> findAllByTestUuid(UUID testUuid) throws ServiceException {
        LOG.debug("Find all questions by test uuid {}", testUuid);
        transactionManager.beginWithAutoCommit(questionDao, testDao, answerDao);
        try {
            List<Question> questions = questionDao.findAllByTestUuid(testUuid);
            return generateQuestionDto(questions);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public List<QuestionDto> findAllOpenToCreateAnswers() throws ServiceException {
        LOG.debug("Find all questions appropriate for creation answers");
        transactionManager.beginWithAutoCommit(questionDao, testDao, subjectDao, answerDao);
        try {
            List<Question> filteredQuestions = new ArrayList<>();
            List<Question> questions = questionDao.findAll();
            for (Question question : questions) {
                if (answerDao.findAllByQuestionUuid(question.getUuid()).isEmpty()) {
                    filteredQuestions.add(question);
                }
            }
            return filteredQuestions.stream()
                    .map(QuestionDto.builder()::fromQuestion)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean deleteByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Deleting question by uuid {}", uuid);
        transactionManager.begin(questionDao);
        try {
            boolean isDeleted;
            isDeleted = questionDao.deleteByUuid(uuid);
            transactionManager.commit();
            return isDeleted;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    private List<StudentTestDto.QuestionForStudentTestDto> generateQuestionDto(List<Question> questions) throws DaoException {
        List<StudentTestDto.QuestionForStudentTestDto> questionsForStudent = new ArrayList<>();
        for (Question question : questions) {
            List<Answer> answers = answerDao.findAllByQuestionUuid(question.getUuid());
            List<StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto> answerDtos = generateAnswerDtos(answers);
            StudentTestDto.QuestionForStudentTestDto questionForStudent = new StudentTestDto.QuestionForStudentTestDto(
                    question.getUuid().toString(),
                    question.getType().toString(),
                    question.getContent(),
                    question.getDescription(),
                    answerDtos
            );
            questionsForStudent.add(questionForStudent);
        }
        return questionsForStudent;
    }

    private List<StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto> generateAnswerDtos(List<Answer> answers) {
        List<StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto> answerDtos = new ArrayList<>();
        for (Answer answer : answers) {
            StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto answerForStudent =
                    new StudentTestDto.QuestionForStudentTestDto.AnswerOnQuestionForStudentTestDto(
                            answer.getUuid().toString(),
                            answer.getContent(),
                            answer.getIsCorrect().toString()
                    );
            answerDtos.add(answerForStudent);
        }
        return answerDtos;
    }
}
