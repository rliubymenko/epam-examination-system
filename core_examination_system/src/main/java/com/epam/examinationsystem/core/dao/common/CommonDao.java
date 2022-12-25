package com.epam.examinationsystem.core.dao.common;

import com.epam.examinationsystem.core.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommonDao<ENTITY extends AbstractEntity> {

    Optional<ENTITY> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    List<ENTITY> findAll();

    boolean create(ENTITY entity);

    boolean update(ENTITY entity);

    void deleteByUuid(UUID uuid);
}
