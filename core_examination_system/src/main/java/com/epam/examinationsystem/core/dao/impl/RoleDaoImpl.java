package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class RoleDaoImpl extends AbstractDao implements RoleDao {

    @Override
    public Optional<Role> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public boolean create(Role entity) {
        return false;
    }

    @Override
    public boolean update(Role entity) {
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }
}
