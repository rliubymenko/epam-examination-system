package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Answer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class AnswerDaoImpl extends AbstractDao implements AnswerDao {

    @Override
    public Optional<Answer> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Answer> findAll() {
        return null;
    }

    @Override
    public boolean create(Answer entity) {
        return false;
    }

    @Override
    public boolean update(Answer entity) {
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }

    @Override
    public long count() {
        return 0;
    }
}
