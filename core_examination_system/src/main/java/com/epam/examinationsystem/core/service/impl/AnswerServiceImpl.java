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
import com.epam.examinationsystem.core.dto.AnswerDto;
import com.epam.examinationsystem.core.dto.QuestionDto;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.AnswerService;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The service implementation for Answer entity.
 */
@PleaseService
public class AnswerServiceImpl implements AnswerService {

    private static final Logger LOG = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @PleaseInject
    private AnswerDao answerDao;

    @PleaseInject
    private QuestionDao questionDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private TransactionManager<Answer> transactionManager;

    /**
     * The method for searching Answer by uuid.
     *
     * @param uuid an uuid of the answer to search.
     * @return an <code>Optional</code> with searched AnswerDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<AnswerDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find answer by uuid {}", uuid);
        if (uuid == null) {
            return Optional.empty();
        }
        transactionManager.beginWithAutoCommit(answerDao, questionDao, testDao);
        try {
            Optional<Answer> maybeAnswer = answerDao.findByUuid(uuid);
            return maybeAnswer.map(AnswerDto.builder()::fromAnswer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching answers by question uuid.
     *
     * @param uuid a question uuid.
     * @return an <code>List</code> with AnswerDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<AnswerDto> findAllByQuestionUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find answer by question uuid {}", uuid);
        transactionManager.beginWithAutoCommit(answerDao, questionDao, testDao);
        try {
            List<Answer> answersByQuestionUuid = answerDao.findAllByQuestionUuid(uuid);
            return answersByQuestionUuid.stream()
                    .map(AnswerDto.builder()::fromAnswer)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for creating the answers for the question.
     *
     * @param questionDto the QuestionDto for which be created the answers.
     * @param answers     the answers that be attached to the question.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public void createAnswersForQuestion(List<AnswerDto> answers, QuestionDto questionDto) throws ServiceException {
        LOG.debug("Creating answers {} for question {}", answers, questionDto);
        transactionManager.begin(answerDao, questionDao, testDao);
        try {
            Optional<Question> question = questionDao.findByUuid(UUID.fromString(questionDto.getUuid()));
            if (question.isPresent()) {
                for (AnswerDto answer : answers) {
                    Answer currentAnswer = Answer.builder()
                            .setContent(answer.getContent())
                            .setIsCorrect(BooleanUtils.toBoolean(answer.getIsCorrect()))
                            .setQuestion(question.get())
                            .build();
                    answerDao.create(currentAnswer);
                }
            }
            transactionManager.commit();
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for updating the answer.
     *
     * @param answerDto the AnswerDto to update.
     * @return true if updated successfully, otherwise false.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean update(AnswerDto answerDto) throws ServiceException {
        LOG.debug("Updating answer by dto {}", answerDto);
        transactionManager.begin(answerDao, questionDao, testDao);
        try {
            boolean isUpdated = false;
            UUID uuid = UUID.fromString(answerDto.getUuid());
            Optional<Answer> maybeAnswer = answerDao.findByUuid(uuid);
            if (maybeAnswer.isPresent()) {
                Answer currentAnswer = maybeAnswer.get();
                QuestionType currentType = currentAnswer.getQuestion().getType();
                boolean isCorrectDto = BooleanUtils.toBoolean(answerDto.getIsCorrect());
                boolean isTextOrNumericalAnswer = currentType.equals(QuestionType.TEXT) || currentType.equals(QuestionType.NUMERICAL);
                Answer toUpdate = Answer.builder()
                        .setUuid(UUID.fromString(answerDto.getUuid()))
                        .setContent(answerDto.getContent())
                        .setIsCorrect(isTextOrNumericalAnswer ? true : isCorrectDto)
                        .setQuestion(currentAnswer.getQuestion())
                        .build();

                if (currentType.equals(QuestionType.SINGLE_CHOICE)) {
                    if (isCorrectDto && !currentAnswer.getIsCorrect()) {
                        List<Answer> answers = answerDao.findAllByQuestionUuid(currentAnswer.getQuestion().getUuid());
                        Answer trueAnswer = answers.stream()
                                .filter(Answer::getIsCorrect)
                                .findFirst()
                                .get();
                        Answer toChange = Answer.builder()
                                .setUuid(trueAnswer.getUuid())
                                .setContent(trueAnswer.getContent())
                                .setIsCorrect(false)
                                .setQuestion(trueAnswer.getQuestion())
                                .build();
                        answerDao.update(toChange);
                        answerDao.update(toUpdate);
                        isUpdated = true;
                    } else if (currentAnswer.getIsCorrect().equals(isCorrectDto)) {
                        answerDao.update(toUpdate);
                        isUpdated = true;
                    }
                } else {
                    answerDao.update(toUpdate);
                    isUpdated = true;
                }
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
     * The method for updating the answer in Single-Choice case and set new true answer by uuid.
     *
     * @param answerDto         the AnswerDto to update.
     * @param newTrueAnswerUuid the new uuid for a true answer.
     * @return true if updated successfully, otherwise false.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean updateAnswerAndSetNewTrueAnswer(AnswerDto answerDto, UUID newTrueAnswerUuid) throws ServiceException {
        LOG.debug("Updating answer by uuid {} and set new true answer by uuid {}", answerDto, newTrueAnswerUuid);
        transactionManager.begin(answerDao, questionDao, testDao);
        try {
            boolean isUpdated = false;
            Optional<Answer> maybeNewTrueAnswer = answerDao.findByUuid(newTrueAnswerUuid);
            if (maybeNewTrueAnswer.isPresent()) {
                Answer newTrueAnswer = maybeNewTrueAnswer.get();
                newTrueAnswer = Answer.builder()
                        .setUuid(newTrueAnswer.getUuid())
                        .setContent(newTrueAnswer.getContent())
                        .setIsCorrect(true)
                        .setQuestion(newTrueAnswer.getQuestion())
                        .build();

                Answer answer = Answer.builder()
                        .setUuid(UUID.fromString(answerDto.getUuid()))
                        .setContent(answerDto.getContent())
                        .setIsCorrect(false)
                        .setQuestion(newTrueAnswer.getQuestion())
                        .build();

                answerDao.update(newTrueAnswer);
                answerDao.update(answer);
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
     * The method for searching all answers by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>DataTableResponse</code> with the queried AnswerDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<AnswerDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all answers by {}", request);
        transactionManager.beginWithAutoCommit(answerDao, questionDao, testDao, subjectDao);
        try {
            List<Answer> answers = answerDao.findAll(request);
            List<Answer> allAnswers = answerDao.findAll();
            Map<UUID, String> questionForSearch = getQuestionsForSearch(allAnswers);
            long countOfEntities = answerDao.count(request);
            DataTableResponse<AnswerDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<AnswerDto> answerDtos = answers.stream()
                    .map(AnswerDto.builder()::fromAnswer)
                    .toList();
            Map<UUID, String> currentQuestionForSearch = getCurrentQuestionForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentQuestionForSearch);
            response.setDtos(answerDtos);
            response.setDataForSearch(List.of(questionForSearch));
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all answers.
     *
     * @return a <code>List</code> with AnswerDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<AnswerDto> findAll() throws ServiceException {
        LOG.debug("Find all answers");
        transactionManager.beginWithAutoCommit(answerDao, questionDao, testDao, subjectDao);
        try {
            return answerDao.findAll()
                    .stream()
                    .map(AnswerDto.builder()::fromAnswer)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for deleting the answer.
     *
     * @param answerUuid   the answer uuid.
     * @param questionUuid the question uuid.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean deleteByUuidAndQuestionUuid(UUID answerUuid, UUID questionUuid) throws ServiceException {
        LOG.debug("Deleting answer by uuid {} and question uuid {}", answerUuid, questionUuid);
        transactionManager.begin(answerDao, questionDao);
        try {
            boolean isDeleted = false;
            Optional<Answer> maybeAnswer = answerDao.findByUuid(answerUuid);
            Optional<Question> maybeQuestion = questionDao.findByUuid(questionUuid);
            if (maybeQuestion.isPresent() && maybeAnswer.isPresent()) {
                boolean answerCorrectness = maybeAnswer.get().getIsCorrect();
                QuestionType type = maybeQuestion.get().getType();
                if (!(type.equals(QuestionType.SINGLE_CHOICE) && answerCorrectness)) {
                    isDeleted = answerDao.deleteByUuid(answerUuid);
                }
            }
            transactionManager.commit();
            return isDeleted;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for deleting the answer in Single-Choice case and set new true answer by uuid.
     *
     * @param answerUuid        the current true answer uuid.
     * @param newTrueAnswerUuid the new uuid for a true answer.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean deleteByUuidAndSetNewTrueAnswer(UUID answerUuid, UUID newTrueAnswerUuid) throws ServiceException {
        LOG.debug("Deleting answer by uuid {} and set new true answer by uuid {}", answerUuid, newTrueAnswerUuid);
        transactionManager.begin(answerDao, questionDao);
        try {
            boolean isDeleted = false;
            Optional<Answer> maybeAnswer = answerDao.findByUuid(answerUuid);
            Optional<Answer> maybeNewTrueAnswer = answerDao.findByUuid(newTrueAnswerUuid);
            if (maybeNewTrueAnswer.isPresent() && maybeAnswer.isPresent()) {
                Answer newTrueAnswer = maybeNewTrueAnswer.get();
                newTrueAnswer = Answer.builder()
                        .setUuid(newTrueAnswer.getUuid())
                        .setContent(newTrueAnswer.getContent())
                        .setIsCorrect(true)
                        .setQuestion(newTrueAnswer.getQuestion())
                        .build();
                answerDao.update(newTrueAnswer);
                answerDao.deleteByUuid(answerUuid);
                isDeleted = true;
            }
            transactionManager.commit();
            return isDeleted;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for checking the existence of the answer by given uuid.
     *
     * @param uuid the uuid of searchable answer.
     * @return true - if record exists by given uuid or false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean existsByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Check if exists by uuid {}", uuid);
        if (uuid == null) {
            return false;
        }
        transactionManager.begin(answerDao);
        try {
            return answerDao.existsByUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    private Map<UUID, String> getQuestionsForSearch(List<Answer> answers) {
        return answers
                .stream()
                .collect(Collectors.toMap(
                        answer -> answer.getQuestion().getUuid(),
                        answer -> answer.getQuestion().getContent(),
                        (firstQuestion, secondQuestion) -> firstQuestion
                ));
    }

    private Map<UUID, String> getCurrentQuestionForSearch(String questionUuid) throws DaoException {
        Map<UUID, String> currentQuestionForSearch = new HashMap<>();
        if (ParameterValidator.isValidUUID(questionUuid) && questionDao.existsByUuid(UUID.fromString(questionUuid))) {
            currentQuestionForSearch = questionDao.findByUuid(UUID.fromString(questionUuid))
                    .stream()
                    .collect(Collectors.toMap(
                            AbstractEntity::getUuid,
                            Question::getContent
                    ));
        }
        return currentQuestionForSearch;
    }
}
