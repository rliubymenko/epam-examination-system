package com.epam.examinationsystem.core.dao.common;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommonDao<ENTITY extends AbstractEntity> {

    Optional<ENTITY> findByUuid(UUID uuid) throws DaoException;

    ENTITY getById(Long id) throws DaoException;

    boolean existsByUuid(UUID uuid) throws DaoException;

    List<ENTITY> findAll() throws DaoException;

    List<ENTITY> findAll(DataTableRequest request) throws DaoException;

    ENTITY create(ENTITY entity) throws DaoException;

    ENTITY update(ENTITY entity) throws DaoException;

    boolean deleteByUuid(UUID uuid) throws DaoException;

    long count() throws DaoException;

    // --- Method for transaction manager
    void setConnection(Connection connection);
}
