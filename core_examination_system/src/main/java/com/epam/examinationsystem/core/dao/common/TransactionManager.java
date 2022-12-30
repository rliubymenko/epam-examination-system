package com.epam.examinationsystem.core.dao.common;

import com.epam.di.annotation.PleaseConnectionManager;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

@PleaseService
public class TransactionManager<E extends AbstractEntity> {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionManager.class);

    @PleaseConnectionManager
    private ConnectionPoolManager connectionManager;
    private Connection connection;

    public void begin(AbstractDao<E> currentDao) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        currentDao.setConnection(connection);
    }

    public void begin(AbstractDao<E> currentDao, AbstractDao<E>... daos) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error("Error while inactive auto commit", e);
        }
        currentDao.setConnection(connection);
        for (AbstractDao<E> dao : daos) {
            dao.setConnection(connection);
        }
    }

    public void end() {
        if (connection != null) {
            try {
                boolean autoCommitMode = connection.getAutoCommit();
                if (!autoCommitMode) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                LOG.error("Error while enable auto commit", e);
            }
            connectionManager.returnConnection(connection);
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Error occurred while committing transaction", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error("Error occurred while rollback transaction", e);
        }
    }
}
