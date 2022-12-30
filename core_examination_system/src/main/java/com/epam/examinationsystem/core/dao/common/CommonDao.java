package com.epam.examinationsystem.core.dao.common;

import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommonDao<ENTITY extends AbstractEntity> {

    Optional<ENTITY> findByUuid(UUID uuid) throws DaoException;

    boolean existsByUuid(UUID uuid) throws DaoException;

    List<ENTITY> findAll() throws DaoException;

    boolean create(ENTITY entity) throws DaoException;

    boolean update(ENTITY entity) throws DaoException;

    boolean deleteByUuid(UUID uuid) throws DaoException;

    long count() throws DaoException;
}
