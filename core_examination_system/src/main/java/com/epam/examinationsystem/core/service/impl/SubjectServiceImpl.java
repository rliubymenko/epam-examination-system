package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class SubjectServiceImpl implements SubjectService {

    private static final Logger LOG = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @PleaseInject
    private SubjectDao subjectDao;

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private RoleDao roleDao;

    @PleaseInject
    private TransactionManager<Subject> transactionManager;

    @Override
    public boolean create(SubjectDto subjectDto) throws ServiceException {
        LOG.debug("Creating subject by dto {}", subjectDto);
        transactionManager.begin(subjectDao, userDao, roleDao);
        try {
            Subject subject = Subject.builder()
                    .setName(subjectDto.getName())
                    .setDescription(subjectDto.getDescription())
                    .build();
            subjectDao.create(subject);
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
    public boolean update(SubjectDto subjectDto) throws ServiceException {
        LOG.debug("Updating subject by dto {}", subjectDto);
        transactionManager.begin(subjectDao, userDao, roleDao);
        try {
            boolean isUpdated = false;
            UUID uuid = UUID.fromString(subjectDto.getUuid());
            Optional<Subject> maybeSubject = subjectDao.findByUuid(uuid);
            if (maybeSubject.isPresent()) {
                Subject currentSubject = maybeSubject.get();
                Subject subject = Subject.builder()
                        .setId(currentSubject.getId())
                        .setUuid(currentSubject.getUuid())
                        .setName(subjectDto.getName())
                        .setDescription(subjectDto.getDescription())
                        .build();
                subjectDao.update(subject);
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
    public Optional<SubjectDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find subject by uuid {}", uuid);
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao);
        try {
            Optional<Subject> maybeSubject = subjectDao.findByUuid(uuid);
            return maybeSubject.map(SubjectDto.builder()::fromSubject);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public List<SubjectDto> findAll() throws ServiceException {
        LOG.debug("Find all subjects");
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao);
        try {
            return subjectDao.findAll()
                    .stream()
                    .map(SubjectDto.builder()::fromSubject)
                    .toList();
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
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao);
        try {
            return subjectDao.existsByUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public DataTableResponse<SubjectDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all subjects by {}", request);
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao);
        try {
            List<Subject> subjects = subjectDao.findAll(request);
            DataTableResponse<SubjectDto> response = PageableUtil.calculatePageableData(request, subjectDao);
            List<SubjectDto> subjectDtos = subjects.stream()
                    .map(SubjectDto.builder()::fromSubject)
                    .toList();
            response.setDtos(subjectDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean existsByName(String name) throws ServiceException {
        LOG.debug("Check if exists by name {}", name);
        if (name == null) {
            return false;
        }
        transactionManager.beginWithAutoCommit(subjectDao);
        try {
            return subjectDao.existsByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean deleteByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Deleting subject by uuid {}", uuid);
        transactionManager.begin(subjectDao);
        try {
            boolean isDeleted;
            isDeleted = subjectDao.deleteByUuid(uuid);
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
