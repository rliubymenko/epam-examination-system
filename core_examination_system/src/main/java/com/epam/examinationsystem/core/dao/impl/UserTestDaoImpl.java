package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.UserTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class UserTestDaoImpl extends AbstractDao implements UserTestDao {

    @Override
    public Optional<UserTest> findByUserUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Optional<UserTest> findByTestUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUserUuid(UUID uuid) {
        return false;
    }

    @Override
    public boolean existsByTestUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<UserTest> findAll() {
        return null;
    }

    @Override
    public boolean create(UserTest entity) {
        return false;
    }

    @Override
    public boolean update(UserTest entity) {
        return false;
    }

    @Override
    public void deleteByUserUuid(UUID uuid) {

    }
}
