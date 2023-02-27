package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.exception.DaoException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserTestDao extends CommonDao<UserTest> {

    UserTest createAndSelect(UserTest userTest) throws DaoException;

    void setStartTime(UUID uuid, LocalDateTime startTime) throws DaoException;

    List<UserTest> findByUserUuid(UUID uuid) throws DaoException;

    List<UserTest> findByTestUuid(UUID uuid) throws DaoException;

    Optional<UserTest> findByUserAndTestUuid(UUID userUuid, UUID testUuid) throws DaoException;

    boolean existsByUserUuid(UUID uuid) throws DaoException;

    boolean existsByTestUuid(UUID uuid) throws DaoException;

    boolean deleteByUserUuid(UUID uuid) throws DaoException;

    boolean deleteByTestUuid(UUID uuid) throws DaoException;
}
