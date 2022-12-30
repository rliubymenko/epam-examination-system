package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.exception.DaoException;

public interface UserDao extends CommonDao<User> {

    User getById(Long id) throws DaoException;
}
