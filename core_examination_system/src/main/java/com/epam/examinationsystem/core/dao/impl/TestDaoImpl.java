package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class TestDaoImpl extends AbstractDao implements TestDao {

    @Override
    public Optional<Test> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Test> findAll() {
        return null;
    }

    @Override
    public boolean create(Test entity) {
        return false;
    }

    @Override
    public boolean update(Test entity) {
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }
}
