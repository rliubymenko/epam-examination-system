package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.exception.DaoException;

public interface SubjectDao extends CommonDao<Subject> {

    boolean existsByName(String name) throws DaoException;
}
