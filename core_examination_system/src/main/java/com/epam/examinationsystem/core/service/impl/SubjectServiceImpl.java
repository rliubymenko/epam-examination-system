package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.StudentSubjectDto;
import com.epam.examinationsystem.core.dto.SubjectDto;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.examinationsystem.core.util.validation.DateUtil;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The service implementation for Subject entity.
 */
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
    private TestService testService;

    @PleaseInject
    private UserTestDao userTestDao;

    @PleaseInject
    private UserTestService userTestService;

    @PleaseInject
    private TransactionManager<Subject> transactionManager;

    /**
     * The method for creating subject.
     *
     * @param subjectDto the SubjectDto instance.
     * @return true if created successfully, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for updating the subject.
     *
     * @param subjectDto the SubjectDto to update.
     * @return true if updated successfully, otherwise false.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching Subject by uuid.
     *
     * @param uuid an uuid of the subject to search.
     * @return an <code>Optional</code> with searched SubjectDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching all subjects.
     *
     * @return a <code>List</code> with SubjectDto.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for checking the existence of the subject by given uuid.
     *
     * @param uuid the uuid of searchable subject.
     * @return true - if record exists by given uuid or false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for searching all subjects by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>DataTableResponse</code> with the queried SubjectDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<SubjectDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all subjects by {}", request);
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao);
        try {
            List<Subject> subjects = subjectDao.findAll(request);
            long countOfEntities = subjectDao.count(request);
            DataTableResponse<SubjectDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
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

    /**
     * The method for searching all subjects by parameters for the student table representation.
     *
     * @param request         the DataTableRequest instance.
     * @param currentUserUuid the uuid of current user.
     * @return a <code>DataTableResponse</code> with the queried SubjectDto and the calculated filters.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
    @Override
    public DataTableResponse<StudentSubjectDto> findAllForStudent(DataTableRequest request, UUID currentUserUuid) throws ServiceException {
        LOG.debug("Find all subjects by {} for student", request);
        transactionManager.beginWithAutoCommit(subjectDao, userDao, roleDao, userTestDao);
        try {
            List<StudentSubjectDto> subjectDtos = new ArrayList<>();
            List<Subject> subjects = subjectDao.findAll(request);
            long countOfEntities = subjectDao.count(request);
            DataTableResponse<StudentSubjectDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            for (Subject subject : subjects) {
                List<Test> tests = testService.findAllBySubjectUuidForStudent(subject.getUuid());
                List<StudentSubjectDto.TestForStudentSubjectDto> subjectTests = new ArrayList<>();
                for (Test test : tests) {
                    int currentAttemptNumber = userTestService.getCurrentAttemptNumber(currentUserUuid, test.getUuid());
                    boolean isSelected = userTestService.isSelected(currentUserUuid, test.getUuid());
                    StudentSubjectDto.TestForStudentSubjectDto testForSubject = new StudentSubjectDto.TestForStudentSubjectDto(
                            test.getUuid().toString(),
                            test.getName(),
                            test.getComplexity().toString(),
                            String.valueOf(test.getMaxAttemptNumber()),
                            String.valueOf(currentAttemptNumber),
                            String.valueOf(BooleanUtils.toStringTrueFalse(isSelected)),
                            test.getExpirationDate() != null ? test.getExpirationDate().toString() : null,
                            String.valueOf(test.getDuration()),
                            DateUtil.compareDateForStudent(test.getExpirationDate())
                    );
                    subjectTests.add(testForSubject);
                }
                subjectDtos.add(StudentSubjectDto.builder().fromSubject(subject, subjectTests));
            }
            response.setDtos(subjectDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    /**
     * The method for checking the existence of the subject by given name.
     *
     * @param name the name of searchable subject.
     * @return true - if record exists by given name or false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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

    /**
     * The method for deleting the subject.
     *
     * @param uuid an uuid of the deletion subject.
     * @return true - is the deletion was successful, false otherwise.
     * @throws ServiceException if a <code>DaoException</code> is occurred.
     */
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
