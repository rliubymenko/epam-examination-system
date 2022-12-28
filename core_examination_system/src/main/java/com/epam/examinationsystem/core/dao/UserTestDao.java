package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.UserTest;

import java.util.Optional;
import java.util.UUID;

public interface UserTestDao extends CommonDao<UserTest> {

    Optional<UserTest> findByUserUuid(UUID uuid);

    Optional<UserTest> findByTestUuid(UUID uuid);

    boolean existsByUserUuid(UUID uuid);

    boolean existsByTestUuid(UUID uuid);

    void deleteByUserUuid(UUID uuid);
}
