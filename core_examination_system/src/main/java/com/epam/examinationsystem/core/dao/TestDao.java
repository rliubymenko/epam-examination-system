package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.exception.DaoException;

public interface TestDao extends CommonDao<Test> {

    Test getById(Long id) throws DaoException;
}
