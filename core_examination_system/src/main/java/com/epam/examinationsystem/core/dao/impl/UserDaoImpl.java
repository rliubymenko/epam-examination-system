package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public Optional<User> findByUuid(UUID uuid) {
//        User user;
//        Optional<User> userOptional;
//        PreparedStatement statement = null;
//        try {
//            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                user = extractUser(resultSet);
//            }
//            userOptional = Optional.ofNullable(user);
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        } finally {
//            close(statement);
//        }
//        return userOptional;
        return null;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
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
