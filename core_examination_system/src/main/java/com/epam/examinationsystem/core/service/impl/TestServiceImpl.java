package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.TestDto;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.util.DateUtil;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private TransactionManager<Test> transactionManager;

    @Override
    public boolean create(TestDto testDto) throws ServiceException {
        LOG.debug("Creating subject by dto {}", testDto);
        transactionManager.begin(testDao, subjectDao, userDao, roleDao);
        try {
            boolean isCreated;
            Subject subject = subjectDao.findByUuid(UUID.fromString(testDto.getSubject().uuid())).get();
            Test test = Test.builder()
                    .setName(testDto.getName())
                    .setDescription(testDto.getDescription())
                    .setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, testDto.getComplexity()))
                    .setDuration(Integer.valueOf(testDto.getDuration()))
                    .setTotalAttemptNumber(0)
                    .setCreationDate(LocalDateTime.now())
                    .setExpirationDate(DateUtil.parseDateTime(testDto.getExpirationDate()))
                    .setMaxAttemptNumber(Integer.valueOf(testDto.getMaxAttemptNumber()))
                    .setSubject(subject)
                    .build();
            isCreated = testDao.create(test);
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
                isUpdated = testDao.update(test);
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

    @Override
    public DataTableResponse<TestDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all tests by {}", request);
        transactionManager.beginWithAutoCommit(testDao, subjectDao, userDao, roleDao);
        try {
            List<Test> tests = testDao.findAll(request);
            DataTableResponse<TestDto> response = PageableUtil.calculatePageableData(request, testDao);
            List<TestDto> testDtos = tests.stream()
                    .map(TestDto.builder()::fromTest)
                    .toList();
            response.setDtos(testDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

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
}
