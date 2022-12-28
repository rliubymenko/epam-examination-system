package com.epam.examinationsystem.core.util;

import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.enumeration.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ResultSetUtil {

    private ResultSetUtil() {
    }

    public static Role extractRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setUuid(UUID.fromString(resultSet.getString("uuid")));
        role.setName(UserType.valueOf(resultSet.getString("name")));
        return role;
    }


}
