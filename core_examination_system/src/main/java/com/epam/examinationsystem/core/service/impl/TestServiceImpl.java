package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.*;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentTestDto;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.DateUtil;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The service implementation for Test entity.
 */
@PleaseService
public class TestServiceImpl implements TestService {

    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private TestDao userDao;

    @PleaseInject
    private RoleDao roleDao;

    @PleaseInject
    private QuestionDao questionDao;

    @PleaseInject
    private AnswerDao answerDao;

    @PleaseInject
    private QuestionService questionService;

    @PleaseInject
    private UserTestDao userTestDao;

    @PleaseInject
    private UserTestService userTestService;

    @PleaseInject
    private TransactionManager<Test> transactionManager;

    /**
     * The method for creating test.
     *
     * @param testDto the TestDto instance.
     * @return true if created successfully, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean create(TestDto testDto) throws ServiceException {
        LOG.debug("Creating subject by dto {}", testDto);
        transactionManager.begin(testDao, subjectDao, userDao, roleDao);
        try {
            boolean isCreated = false;
            Optional<Subject> maybeSubject = subjectDao.findByUuid(UUID.fromString(testDto.getSubject().uuid()));
            if (maybeSubject.isPresent()) {
                Test test = Test.builder()
                        .setName(testDto.getName())
                        .setDescription(testDto.getDescription())
                        .setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, testDto.getComplexity()))
                        .setDuration(Integer.valueOf(testDto.getDuration()))
                        .setTotalAttemptNumber(0)
                        .setExpirationDate(DateUtil.parseDateTime(testDto.getExpirationDate()))
                        .setMaxAttemptNumber(Integer.valueOf(testDto.getMaxAttemptNumber()))
                        .setSubject(maybeSubject.get())
                        .build();
                testDao.create(test);
                isCreated = true;
            }
            transactionManager.commit();
            return isCreated;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for updating the test.
     *
     * @param testDto the TestDto to update.
     * @return true if updated successfully, otherwise false.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean update(TestDto testDto) throws ServiceException {
        LOG.debug("Updating test by dto {}", testDto);
        transactionManager.begin(testDao, subjectDao, userDao, roleDao);
        try {
            boolean isUpdated = false;
            UUID uuid = UUID.fromString(testDto.getUuid());
            Optional<Test> maybeTest = testDao.findByUuid(uuid);
            if (maybeTest.isPresent()) {
                Test currentTest = maybeTest.get();
                Test test = Test.builder()
                        .setId(currentTest.getId())
                        .setUuid(currentTest.getUuid())
                        .setName(testDto.getName())
                        .setDescription(testDto.getDescription())
                        .setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, testDto.getComplexity()))
                        .setDuration(Integer.valueOf(testDto.getDuration()))
                        .setExpirationDate(DateUtil.parseDateTime(testDto.getExpirationDate()))
                        .setMaxAttemptNumber(Integer.valueOf(testDto.getMaxAttemptNumber()))
                        .build();
                testDao.update(test);
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
     * The method for searching Test by uuid.
     *
     * @param uuid an uuid of the test to search.
     * @return an <code>Optional</code> with searched TestDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<TestDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find test by uuid {}", uuid);
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            Optional<Test> maybeTest = testDao.findByUuid(uuid);
            return maybeTest.map(TestDto.builder()::fromTest);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching tests by subject uuid.
     *
     * @param uuid the subject uuid.
     * @return <code>List</code> with Test entities.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<Test> findAllBySubjectUuidForStudent(UUID uuid) throws ServiceException {
        LOG.debug("Find all tests for student with subject uuid: {}", uuid);
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            return testDao.findAllBySubjectUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching test for testing.
     *
     * @param uuid the test uuid.
     * @return <code>Optional</code> with StudentTestDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<StudentTestDto> findByUuidForTesting(UUID uuid) throws ServiceException {
        LOG.debug("Find test for testing by uuid {}", uuid);
        transactionManager.beginWithAutoCommit(testDao, questionDao, answerDao);
        try {
            Optional<Test> maybeTest = testDao.findByUuidWithoutSubject(uuid);
            List<StudentTestDto.QuestionForStudentTestDto> questions = new ArrayList<>();
            if (maybeTest.isPresent()) {
                questions = questionService.findAllByTestUuid(maybeTest.get().getUuid());
            }
            List<StudentTestDto.QuestionForStudentTestDto> finalQuestions = questions;
            return maybeTest.map(test -> StudentTestDto.builder().fromTest(test, finalQuestions));
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for checking the existence of the test by given uuid.
     *
     * @param uuid the uuid of searchable test.
     * @return true - if record exists by given uuid or false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean existsByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Check if exists by uuid {}", uuid);
        if (uuid == null) {
            return false;
        }
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            return testDao.existsByUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all tests by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>DataTableResponse</code> with the queried TestDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<TestDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all tests by {}", request);
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            List<Test> tests = testDao.findAll(request);
            List<Test> allTests = testDao.findAll();
            Map<UUID, String> subjectsForSearch = getSubjectsForSearch(allTests);
            long countOfEntities = testDao.count(request);
            DataTableResponse<TestDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<TestDto> testDtos = tests.stream()
                    .map(TestDto.builder()::fromTest)
                    .toList();
            Map<UUID, String> currentSubjectForSearch = getCurrentSubjectForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentSubjectForSearch);
            response.setDataForSearch(List.of(subjectsForSearch));
            response.setDtos(testDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all tests by parameters for the student table representation.
     *
     * @param request         the DataTableRequest instance.
     * @param currentUserUuid the current user uuid.
     * @return a <code>DataTableResponse</code> with the queried TestDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<TestDto> findAllForStudent(DataTableRequest request, UUID currentUserUuid) throws ServiceException {
        LOG.debug("Find all tests by {}", request);
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao, userTestDao);
        try {
            List<Test> tests = testDao.findAllForStudent(request);
            List<Test> allTests = testDao.findAllForStudent();
            Map<UUID, String> subjectsForSearch = getSubjectsForSearch(allTests);
            long countOfEntities = testDao.countForStudent(request);
            DataTableResponse<TestDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<TestDto> testDtos = new ArrayList<>();
            for (Test test : tests) {
                int currentAttemptNumber = userTestService.getCurrentAttemptNumber(currentUserUuid, test.getUuid());
                boolean isSelected = userTestService.isSelected(currentUserUuid, test.getUuid());
                Subject testSubject = test.getSubject();
                TestDto.SubjectForTest subjectForTest = null;
                if (testSubject != null) {
                    subjectForTest = new TestDto.SubjectForTest(testSubject.getUuid().toString(), testSubject.getName());
                }
                TestDto testDto = TestDto.builder()
                        .setUuid(test.getUuid().toString())
                        .setName(test.getName())
                        .setDescription(test.getDescription())
                        .setComplexity(test.getComplexity().toString())
                        .setDuration(String.valueOf(test.getDuration()))
                        .setTotalAttemptNumber(String.valueOf(test.getTotalAttemptNumber()))
                        .setCurrentAttemptNumber(String.valueOf(currentAttemptNumber))
                        .setIsSelected(BooleanUtils.toStringTrueFalse(isSelected))
                        .setCreationDate(test.getCreated().toString())
                        .setExpirationDate(test.getExpirationDate() != null ? test.getExpirationDate().toString() : null)
                        .setMaxAttemptNumber(String.valueOf(test.getMaxAttemptNumber()))
                        .setIsAvailable(DateUtil.compareDateForStudent(test.getExpirationDate()))
                        .setSubject(subjectForTest)
                        .build();
                testDtos.add(testDto);
            }
            Map<UUID, String> currentSubjectForSearch = getCurrentSubjectForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentSubjectForSearch);
            response.setDataForSearch(List.of(subjectsForSearch));
            response.setDtos(testDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all tests.
     *
     * @return a <code>List</code> with TestDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<TestDto> findAll() throws ServiceException {
        LOG.debug("Find all tests");
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            return testDao.findAll()
                    .stream()
                    .map(TestDto.builder()::fromTest)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for deleting the test.
     *
     * @param uuid an uuid of the deletion test.
     * @return true - is the deletion was successful, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean deleteByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Deleting test by uuid {}", uuid);
        transactionManager.begin(testDao);
        try {
            boolean isDeleted;
            isDeleted = testDao.deleteByUuid(uuid);
            transactionManager.commit();
            return isDeleted;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    private Map<UUID, String> getSubjectsForSearch(List<Test> tests) {
        return tests
                .stream()
                .filter(test -> test.getSubject() != null)
                .collect(Collectors.toMap(
                        test -> test.getSubject().getUuid(),
                        test -> test.getSubject().getName(),
                        (firstSubject, secondSubject) -> firstSubject
                ));
    }

    private Map<UUID, String> getCurrentSubjectForSearch(String subjectUuid) throws DaoException {
        Map<UUID, String> currentSubjectForSearch = new HashMap<>();
        if (ParameterValidator.isValidUUID(subjectUuid) && subjectDao.existsByUuid(UUID.fromString(subjectUuid))) {
            currentSubjectForSearch = subjectDao.findByUuid(UUID.fromString(subjectUuid))
                    .stream()
                    .collect(Collectors.toMap(
                            AbstractEntity::getUuid,
                            Subject::getName
                    ));
        }
        return currentSubjectForSearch;
    }
}
