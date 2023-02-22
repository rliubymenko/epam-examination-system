package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.Optional;
import java.util.UUID;

public interface UserDao extends CommonDao<User> {

    boolean resetPassword(UUID userUuid, String newPassword) throws DaoException;

    Optional<User> findByUsername(String username) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;

    boolean updateWithoutChangingActivation(User user) throws DaoException;
}
