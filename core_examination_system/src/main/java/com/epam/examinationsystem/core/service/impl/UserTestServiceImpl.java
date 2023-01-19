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
import java.util.Optional;
import java.util.UUID;

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
            boolean isCreated = false;
            Optional<UserTest> currentUserTest = userTestDao.findByTestUuid(UUID.fromString(userTestDto.getTest().getUuid()));
            Optional<User> currentUser = userDao.findByUuid(UUID.fromString(userTestDto.getUser().getUuid()));
            Optional<Test> currentTest = testDao.findByUuid(UUID.fromString(userTestDto.getTest().getUuid()));
            if (currentUserTest.isPresent()) {

            } else {
                LocalDateTime startTime = DateUtil.parseDateTime(userTestDto.getStartTime());
                LocalDateTime endTime = DateUtil.parseDateTime(userTestDto.getEndTime());
                UserTest userTest = UserTest.builder()
                        .setUser(currentUser.get())
                        .setTest(currentTest.get())
                        .setIsSelected(true)
                        .setIsCompleted(true)
                        .setMarkValue(Float.parseFloat(userTestDto.getMarkValue()))
                        .setAttemptNumber(1)
                        .setStartTime(startTime)
                        .setEndTime(endTime)
                        .build();
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
}
