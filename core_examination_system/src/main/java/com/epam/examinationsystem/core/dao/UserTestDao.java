package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.entity.UserTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserTestDao {

    Optional<UserTest> findByUserUuid(UUID uuid);

    Optional<UserTest> findByTestUuid(UUID uuid);

    boolean existsByUserUuid(UUID uuid);

    boolean existsByTestUuid(UUID uuid);

    List<UserTest> findAll();

    boolean create(UserTest entity);

    boolean update(UserTest entity);

    void deleteByUserUuid(UUID uuid);
}
