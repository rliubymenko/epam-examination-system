package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class QuestionDaoImpl extends AbstractDao implements QuestionDao {

    @Override
    public Optional<Question> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Question> findAll() {
        return null;
    }

    @Override
    public boolean create(Question entity) {
        return false;
    }

    @Override
    public boolean update(Question entity) {
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
