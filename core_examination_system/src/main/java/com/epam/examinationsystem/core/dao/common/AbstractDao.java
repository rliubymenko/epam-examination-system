package com.epam.examinationsystem.core.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
    protected Connection connection;

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            LOG.error("Error occurred while closing statement", e);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
