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
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The service implementation for Question entity.
 */
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

    /**
     * The method for creating question.
     *
     * @param questionDto the QuestionDto instance.
     * @return an <code>QuestionDto</code> that was created.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for updating the question.
     *
     * @param questionDto the QuestionDto to update.
     * @return true if updated successfully, otherwise false.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching Question by uuid.
     *
     * @param uuid an uuid of the question to search.
     * @return an <code>Optional</code> with searched QuestionDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<QuestionDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find question by uuid {}", uuid);
        if (uuid == null) {
            return Optional.empty();
        }
        transactionManager.beginWithAutoCommit(questionDao, testDao, subjectDao);
        try {
            Optional<Question> maybeQuestion = questionDao.findByUuid(uuid);
            return maybeQuestion.map(QuestionDto.builder()::fromQuestion);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for checking the existence of the question by given uuid.
     *
     * @param uuid the uuid of searchable question.
     * @return true - if record exists by given uuid or false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching all questions by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>DataTableResponse</code> with the queried QuestionDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<QuestionDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all questions by {}", request);
        transactionManager.beginWithAutoCommit(questionDao, testDao, answerDao, subjectDao);
        try {
            List<Question> questions = questionDao.findAll(request);
            List<Question> allQuestions = questionDao.findAll();
            Map<UUID, String> testsForSearch = getTestsForSearch(allQuestions);
            long countOfEntities = questionDao.count(request);
            DataTableResponse<QuestionDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<QuestionDto> questionDtos = questions.stream()
                    .map(QuestionDto.builder()::fromQuestion)
                    .toList();
            Map<UUID, String> currentTestForSearch = getCurrentTestForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentTestForSearch);
            response.setDataForSearch(List.of(testsForSearch));
            response.setDtos(questionDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all questions.
     *
     * @return a <code>List</code> with QuestionDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<QuestionDto> findAll() throws ServiceException {
        LOG.debug("Find all questions");
        transactionManager.beginWithAutoCommit(questionDao, testDao, answerDao, subjectDao);
        try {
            return questionDao.findAll()
                    .stream()
                    .map(QuestionDto.builder()::fromQuestion)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching questions by test uuid.
     *
     * @param testUuid the test uuid.
     * @return <code>List</code> with StudentTestDto.QuestionForStudentTestDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching questions opened for creating answers.
     *
     * @return <code>List</code> with QuestionDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for deleting the question.
     *
     * @param uuid an uuid of the deletion question.
     * @return true - is the deletion was successful, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    private Map<UUID, String> getTestsForSearch(List<Question> questions) {
        return questions
                .stream()
                .collect(Collectors.toMap(
                        question -> question.getTest().getUuid(),
                        question -> question.getTest().getName(),
                        (firstTest, secondTest) -> firstTest
                ));
    }

    private Map<UUID, String> getCurrentTestForSearch(String testUuid) throws DaoException {
        Map<UUID, String> currentTestForSearch = new HashMap<>();
        if (ParameterValidator.isValidUUID(testUuid) && testDao.existsByUuid(UUID.fromString(testUuid))) {
            currentTestForSearch = testDao.findByUuid(UUID.fromString(testUuid))
                    .stream()
                    .collect(Collectors.toMap(
                            AbstractEntity::getUuid,
                            Test::getName
                    ));
        }
        return currentTestForSearch;
    }
}
