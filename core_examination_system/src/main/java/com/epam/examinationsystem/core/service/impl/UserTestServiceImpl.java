package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@PleaseService
public class UserTestServiceImpl implements UserTestService {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestServiceImpl.class);

    @PleaseInject
    private UserTestDao userTestDao;

    @PleaseInject
    private TestDao testDao;

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private RoleDao roleDao;

    @PleaseInject
    private TransactionManager<UserTest> transactionManager;

    @Override
    public boolean create(UserTestDto userTestDto) throws ServiceException {
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
            userTestDao.create(userTest);
            transactionManager.commit();
            return true;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean createAfterExam(UserTestDto userTestDto) throws ServiceException {
        LOG.debug("Creating userTest by dto {}", userTestDto);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
        try {
            boolean isCreated = false;
            LocalDateTime startTime = DateUtil.parseDateTime(userTestDto.getStartTime());
            LocalDateTime endTime = DateUtil.parseDateTime(userTestDto.getEndTime());
            UUID userUuid = UUID.fromString(userTestDto.getUser().getUuid());
            UUID testUuid = UUID.fromString(userTestDto.getTest().getUuid());
            Optional<UserTest> maybeUserTest = userTestDao.findByUserAndTestUuid(userUuid, testUuid);
            Optional<User> currentUser = userDao.findByUuid(userUuid);
            Optional<Test> currentTest = testDao.findByUuid(testUuid);
            if (maybeUserTest.isPresent()) {
                if (!Objects.equals(currentTest.get().getMaxAttemptNumber(), maybeUserTest.get().getAttemptNumber())) {
                    UserTest currentUserTest = maybeUserTest.get();
                    float currentMarkValue = Float.parseFloat(userTestDto.getMarkValue());
                    float markValueToSave = currentUserTest.getMarkValue() != null && currentUserTest.getMarkValue() < currentMarkValue ? currentMarkValue : currentUserTest.getMarkValue();
                    UserTest userTest = UserTest.builder()
                            .setUuid(currentUserTest.getUuid())
                            .setIsSelected(true)
                            .setIsCompleted(true)
                            .setMarkValue(markValueToSave)
                            .setAttemptNumber(currentUserTest.getAttemptNumber() != null ? currentUserTest.getAttemptNumber() + 1 : 1)
                            .setStartTime(startTime)
                            .setEndTime(endTime)
                            .build();
                    userTestDao.update(userTest);
                    isCreated = true;
                }
            } else {
                Test test = currentTest.get();
                UserTest userTest = UserTest.builder()
                        .setUser(currentUser.get())
                        .setTest(test)
                        .setIsSelected(true)
                        .setIsCompleted(true)
                        .setMarkValue(Float.parseFloat(userTestDto.getMarkValue()))
                        .setAttemptNumber(1)
                        .setStartTime(startTime)
                        .setEndTime(endTime)
                        .build();
                testDao.incrementTotalAttemptNumber(test);
                userTestDao.create(userTest);
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

    @Override
    public List<UserTestDto> findByUserUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find user test by user uuid {}", uuid);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
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

    public int getCurrentAttemptNumber(UUID userUuid, UUID testUuid) throws ServiceException {
        LOG.debug("Get current attempt number for user by user uuid {} and test uuid {}", userUuid, testUuid);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
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

    public boolean isSelected(UUID userUuid, UUID testUuid) throws ServiceException {
        LOG.debug("Get is selected value for user by user uuid {} and test uuid {}", userUuid, testUuid);
        transactionManager.begin(userTestDao, testDao, userDao, roleDao);
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
}
