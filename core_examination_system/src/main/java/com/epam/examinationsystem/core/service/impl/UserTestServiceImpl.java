package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.*;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.DateUtil;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The service implementation for UserTest entity.
 */
@PleaseService
public class UserTestServiceImpl implements UserTestService {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestServiceImpl.class);

    @PleaseInject
    private UserTestDao userTestDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private RoleDao roleDao;

    @PleaseInject
    private TransactionManager<UserTest> transactionManager;

    /**
     * The method for creating UserTest.
     *
     * @param userTestDto the UserTest instance.
     * @return true if created successfully, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public UserTestDto create(UserTestDto userTestDto) throws ServiceException {
        LOG.debug("Creating userTest by dto {}", userTestDto);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
        try {
            UUID userUuid = UUID.fromString(userTestDto.getUser().getUuid());
            UUID testUuid = UUID.fromString(userTestDto.getTest().getUuid());
            Optional<User> currentUser = userDao.findByUuid(userUuid);
            Optional<Test> currentTest = testDao.findByUuid(testUuid);
            UserTest userTest = UserTest.builder()
                    .setUser(currentUser.get())
                    .setTest(currentTest.get())
                    .setIsSelected(true)
                    .build();
            UserTest savedUserTest = userTestDao.createAndSelect(userTest);
            transactionManager.commit();
            return UserTestDto.builder().fromUserTest(savedUserTest);
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for updating UserTest after an exam.
     *
     * @param userTestDto the UserTest instance.
     * @return true if updated successfully, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean updateAfterExam(UserTestDto userTestDto) throws ServiceException {
        LOG.debug("Creating userTest by dto {}", userTestDto);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
        try {
            boolean isCreated = false;
            LocalDateTime endTime = DateUtil.parseDateTime(userTestDto.getEndTime());
            UUID userUuid = UUID.fromString(userTestDto.getUser().getUuid());
            UUID testUuid = UUID.fromString(userTestDto.getTest().getUuid());
            Optional<UserTest> maybeUserTest = userTestDao.findByUserAndTestUuid(userUuid, testUuid);
            Optional<Test> currentTest = testDao.findByUuid(testUuid);
            if (!Objects.equals(currentTest.get().getMaxAttemptNumber(), maybeUserTest.get().getAttemptNumber())) {
                UserTest currentUserTest = maybeUserTest.get();
                float currentMarkValue = Float.parseFloat(userTestDto.getMarkValue());
                float markValueToSave = currentUserTest.getMarkValue() != null && currentUserTest.getMarkValue() > currentMarkValue ? currentUserTest.getMarkValue() : currentMarkValue;
                UserTest userTest = UserTest.builder()
                        .setUuid(currentUserTest.getUuid())
                        .setIsSelected(true)
                        .setIsCompleted(true)
                        .setMarkValue(markValueToSave)
                        .setAttemptNumber(currentUserTest.getAttemptNumber() + 1)
                        .setStartTime(currentUserTest.getStartTime())
                        .setEndTime(endTime)
                        .build();
                userTestDao.update(userTest);
                testDao.incrementTotalAttemptNumber(currentTest.get());
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
     * The method for changing UserTest start time.
     *
     * @param uuid      the UserTest uuid.
     * @param startTime the new start time to set.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public void setStartTime(UUID uuid, LocalDateTime startTime) throws ServiceException {
        LOG.debug("Setting start time for usertest with uuid {}", uuid);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
        try {
            userTestDao.setStartTime(uuid, startTime);
            transactionManager.commit();
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching usertests by user uuid.
     *
     * @param uuid the user uuid.
     * @return <code>List</code> with UserTestDto entities.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<UserTestDto> findByUserUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find user test by user uuid {}", uuid);
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, roleDao);
        try {
            List<UserTest> userTests = userTestDao.findByUserUuid(uuid);
            return userTests
                    .stream()
                    .map(UserTestDto.builder()::fromUserTest)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching usertests by the user uuid and the test uuid.
     *
     * @param userUuid the user uuid.
     * @param testUuid the test uuid.
     * @return <code>Optional</code> with UserTestDto entry.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<UserTestDto> findByUserAndTestUuid(UUID userUuid, UUID testUuid) throws ServiceException {
        LOG.debug("Find user test by user uuid {} and test uuid {}", userUuid, testUuid);
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, roleDao);
        try {
            Optional<UserTest> userTest = userTestDao.findByUserAndTestUuid(userUuid, testUuid);
            return userTest.map(UserTestDto.builder()::fromUserTest);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for retrieving current attempt number in the UserTest entry by the user uuid and the test uuid.
     *
     * @param userUuid the user uuid.
     * @param testUuid the test uuid.
     * @return the current attempt number.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public int getCurrentAttemptNumber(UUID userUuid, UUID testUuid) throws ServiceException {
        LOG.debug("Get current attempt number for user by user uuid {} and test uuid {}", userUuid, testUuid);
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, roleDao);
        try {
            int currentAttemptNumber = 0;
            Optional<UserTest> maybeUserTest = userTestDao.findByUserAndTestUuid(userUuid, testUuid);
            if (maybeUserTest.isPresent()) {
                currentAttemptNumber = maybeUserTest.get().getAttemptNumber();
            }
            return currentAttemptNumber;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for retrieving selected status in the UserTest entry by the user uuid and the test uuid.
     *
     * @param userUuid the user uuid.
     * @param testUuid the test uuid.
     * @return the current attempt number.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public boolean isSelected(UUID userUuid, UUID testUuid) throws ServiceException {
        LOG.debug("Get is selected value for user by user uuid {} and test uuid {}", userUuid, testUuid);
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, roleDao);
        try {
            boolean isSelected = false;
            Optional<UserTest> maybeUserTest = userTestDao.findByUserAndTestUuid(userUuid, testUuid);
            if (maybeUserTest.isPresent()) {
                isSelected = maybeUserTest.get().getIsSelected();
            }
            return isSelected;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all usertests by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>DataTableResponse</code> with the queried UserTestDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<UserTestDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all usertests by {}", request);
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, subjectDao, roleDao);
        try {
            List<UserTest> userTests = userTestDao.findAll(request);
            List<UserTest> allUserTests = userTestDao.findAll();
            Map<UUID, String> usersForSearch = getUsersForSearch(allUserTests);
            Map<UUID, String> testsForSearch = getTestsForSearch(allUserTests);
            long countOfEntities = userTestDao.count(request);
            DataTableResponse<UserTestDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<UserTestDto> userDtos = userTests.stream()
                    .map(UserTestDto.builder()::fromUserTest)
                    .toList();
            Map<UUID, String> currentDataForSearch = getCurrentDataForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentDataForSearch);
            response.setDataForSearch(List.of(usersForSearch, testsForSearch));
            response.setDtos(userDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for searching all usertests.
     *
     * @return a <code>List</code> with UserTestDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public List<UserTestDto> findAll() throws ServiceException {
        LOG.debug("Find all user tests ");
        transactionManager.beginWithAutoCommit(userTestDao, testDao, userDao, subjectDao, roleDao);
        try {
            return userTestDao.findAll()
                    .stream()
                    .map(UserTestDto.builder()::fromUserTest)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    private Map<UUID, String> getTestsForSearch(List<UserTest> userTests) {
        return userTests
                .stream()
                .collect(Collectors.toMap(
                        userTest -> userTest.getTest().getUuid(),
                        userTest -> userTest.getTest().getName(),
                        (firstTest, secondTest) -> firstTest
                ));
    }

    private Map<UUID, String> getUsersForSearch(List<UserTest> userTests) {
        return userTests
                .stream()
                .collect(Collectors.toMap(
                        userTest -> userTest.getUser().getUuid(),
                        userTest -> userTest.getUser().getUsername(),
                        (firstUser, secondUser) -> firstUser
                ));
    }

    private Map<UUID, String> getCurrentDataForSearch(String uuidString) throws DaoException {
        Map<UUID, String> currentDataForSearch = new HashMap<>();
        if (ParameterValidator.isValidUUID(uuidString)) {
            UUID uuid = UUID.fromString(uuidString);
            if (userDao.existsByUuid(uuid)) {
                currentDataForSearch = userDao.findByUuid(uuid)
                        .stream()
                        .collect(Collectors.toMap(
                                AbstractEntity::getUuid,
                                User::getUsername
                        ));
            }
            if (testDao.existsByUuid(uuid)) {
                currentDataForSearch = testDao.findByUuid(uuid)
                        .stream()
                        .collect(Collectors.toMap(
                                AbstractEntity::getUuid,
                                Test::getName
                        ));
            }
        }
        return currentDataForSearch;
    }
}
