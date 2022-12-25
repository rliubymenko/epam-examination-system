package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.MarkDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Mark;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class MarkDaoImpl extends AbstractDao implements MarkDao {

    @Override
    public Optional<Mark> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Mark> findAll() {
        return null;
    }

    @Override
    public boolean create(Mark entity) {
        return false;
    }

    @Override
    public boolean update(Mark entity) {
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }
}
