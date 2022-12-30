package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.exception.DaoException;

public interface SubjectDao extends CommonDao<Subject> {

    Subject getById(Long id) throws DaoException;
}
