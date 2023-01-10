package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PleaseService
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

    private static final String ENTITY_NAME = "role";
    private static final Logger LOG = LoggerFactory.getLogger(RoleDaoImpl.class);

    public RoleDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.ROLE_TABLE_NAME.getValue());
    }

    @Override
    public Optional<Role> findByUserType(UserType userType) throws DaoException {
        Optional<Role> maybeRole;
        LoggerUtil.findByUserTypeStartLogging(LOG, ENTITY_NAME, userType);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByWrappedValueQuery(DaoConstant.ROLE_TABLE_NAME.getValue(), "name", userType.toString());
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                Role role = extractEntity(resultSet);
                maybeRole = Optional.ofNullable(role);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByUserTypeErrorLogging(LOG, ENTITY_NAME, userType);
            throw new DaoException(message, e);
        }
        return maybeRole;
    }

    @Override
    public long count() {
        // Default number of roles
        return 3;
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
    public boolean deleteByUuid(UUID uuid) {
        // Role types is immutable
        return false;
    }

    @Override
    public Role extractEntity(ResultSet resultSet) throws SQLException {
        Role role = null;
        while (resultSet.next()) {
            role = DaoMapperUtil.extractRole(resultSet);
        }
        return role;
    }

    @Override
    public List<Role> extractEntities(ResultSet resultSet) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (resultSet.next()) {
            Role role = DaoMapperUtil.extractRole(resultSet);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public String getInsertQuery() {
        // Role types is immutable
        return null;
    }

    @Override
    public String getUpdateQuery(Role entity) {
        // Role types is immutable
        return null;
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Role entity) {
        // Role types is immutable
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Role entity) {
        // Role types is immutable
    }
}
