package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class SubjectDaoImpl extends AbstractDao implements SubjectDao {

    @Override
    public Optional<Subject> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Subject> findAll() {
        return null;
    }

    @Override
    public boolean create(Subject entity) {
        return false;
    }

    @Override
    public boolean update(Subject entity) {
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }
}
