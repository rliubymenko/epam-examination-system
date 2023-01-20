package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TestDao extends CommonDao<Test> {

    List<Test> findAllBySubjectUuid(UUID uuid) throws DaoException;

    Optional<Test> findByUuidWithoutSubject(UUID uuid) throws DaoException;

    boolean incrementTotalAttemptNumber(Test test) throws DaoException;
}
