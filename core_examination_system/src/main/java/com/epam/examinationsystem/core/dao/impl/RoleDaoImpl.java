package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.util.QueryBuilderUtil;
import com.epam.examinationsystem.core.util.ResultSetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class RoleDaoImpl extends AbstractDao implements RoleDao {

    private static final String ENTITY_NAME = "role";
    private static final Logger LOG = LoggerFactory.getLogger(RoleDaoImpl.class);

    @Override
    public Optional<Role> findByUuid(UUID uuid) {
        Role role = null;
        Optional<Role> maybeRole;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String findQuery = QueryBuilderUtil.generateFindByUuidQuery(DaoConstant.ROLE_TABLE_NAME.getValue(), uuid);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                while (resultSet.next()) {
                    role = ResultSetUtil.extractRole(resultSet);
                }
                maybeRole = Optional.ofNullable(role);
            }
        } catch (SQLException e) {
            LOG.debug("Error occurred while trying to find " + ENTITY_NAME + " by uuid");
            throw new RuntimeException(e);
        } finally {
            close(statement);
        }
        return maybeRole;
    }

    @Override
    public Optional<Role> findByUserType(UserType userType) {
        Role role = null;
        Optional<Role> maybeRole;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String findQuery = QueryBuilderUtil.generateFindByPropertyQuery(DaoConstant.ROLE_TABLE_NAME.getValue(), "name", userType.toString());
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                while (resultSet.next()) {
                    role = ResultSetUtil.extractRole(resultSet);
                }
                maybeRole = Optional.ofNullable(role);
            }
        } catch (SQLException e) {
            LOG.debug("Error occurred while trying to find " + ENTITY_NAME + " by user type");
            throw new RuntimeException(e);
        } finally {
            close(statement);
        }
        return maybeRole;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        long count = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String countQuery = QueryBuilderUtil.generateCountByUuidQuery(DaoConstant.ROLE_TABLE_NAME.getValue(), uuid);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    count = resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            LOG.debug("Error occurred while trying to find and count " + ENTITY_NAME + "s by uuid");
            throw new RuntimeException(e);
        } finally {
            close(statement);
        }
        return count == 1;
    }

    @Override
    public List<Role> findAll() {
        Statement statement = null;
        List<Role> roles = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String findAllQuery = QueryBuilderUtil.generateFindAllQuery(DaoConstant.ROLE_TABLE_NAME.getValue());
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                while (resultSet.next()) {
                    Role role = ResultSetUtil.extractRole(resultSet);
                    roles.add(role);
                }
            }
        } catch (SQLException e) {
            LOG.debug("Error occurred while trying to find all " + ENTITY_NAME + "s");
            throw new RuntimeException(e);
        } finally {
            close(statement);
        }
        return roles;
    }

    @Override
    public boolean create(Role entity) {
        // Role types is immutable
        return false;
    }

    @Override
    public boolean update(Role entity) {
        // Role types is immutable
        return false;
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        // Role types is immutable
    }

    @Override
    public long count() {
        // Default number of roles
        return 3;
    }
}
