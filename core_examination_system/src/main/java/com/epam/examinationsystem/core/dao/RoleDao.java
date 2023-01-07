package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.Optional;

public interface RoleDao extends CommonDao<Role> {

    Optional<Role> findByUserType(UserType userType) throws DaoException;
}
