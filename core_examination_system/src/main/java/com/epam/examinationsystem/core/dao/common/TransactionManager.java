package com.epam.examinationsystem.core.dao.common;

import com.epam.di.annotation.PleaseConnectionManager;
import com.epam.di.annotation.PleaseService;
import com.epam.di.connectionpool.ConnectionPoolManager;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The transaction manager class that helps to interact with  multiple dao simultaneously and brings the manual control over a transaction.
 */
@PleaseService
public class TransactionManager<ENTITY extends AbstractEntity> {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionManager.class);

    @PleaseConnectionManager
    private ConnectionPoolManager connectionManager;
    private Connection connection;

    /**
     * The method uses for beginning the transaction process.
     * The transaction status => autoCommit = true.
     *
     * @param currentDao the dao candidate to inject connection inside.
     */
    public void begin(CommonDao<ENTITY> currentDao) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        currentDao.setConnection(connection);
    }

    /**
     * The method uses for beginning the transaction process.
     * The transaction status => autoCommit = false.
     *
     * @param currentDao the dao candidate to inject connection inside.
     * @param daos       the dao candidates to inject connection inside.
     */
    @SafeVarargs
    public final void begin(CommonDao<ENTITY> currentDao, CommonDao... daos) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error("Error while inactive auto commit", e);
        }
        currentDao.setConnection(connection);
        for (CommonDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    /**
     * The method uses for beginning the transaction process.
     * The transaction status => autoCommit = true.
     *
     * @param currentDao the dao candidate to inject connection inside.
     * @param daos       the dao candidates to inject connection inside.
     */
    @SafeVarargs
    public final void beginWithAutoCommit(CommonDao<ENTITY> currentDao, CommonDao... daos) {
        if (connection == null) {
            connection = connectionManager.getConnection();
        }
        currentDao.setConnection(connection);
        for (CommonDao dao : daos) {
            dao.setConnection(connection);
        }
    }

    /**
     * The method uses for finishing the transaction process.
     * If the transaction status => autoCommit = false, then set autoCommit = true .
     * If the transaction status => autoCommit = true, then do nothing.
     */
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

    /**
     * The method uses for committing the transaction.
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOG.error("Error occurred while committing transaction", e);
        }
    }

    /**
     * The method uses to rollback the transaction.
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOG.error("Error occurred while rollback transaction", e);
        }
    }
}
