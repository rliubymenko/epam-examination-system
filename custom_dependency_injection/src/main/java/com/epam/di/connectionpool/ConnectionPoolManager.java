package com.epam.di.connectionpool;

import com.epam.di.exception.ConnectionPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionPoolManager {
    INSTANCE;

    private final Logger LOG = LoggerFactory.getLogger(ConnectionPoolManager.class);
    private static final int DEFAULT_CONNECTION_POOL_SIZE = 32;
    private static final ReentrantLock getLock = new ReentrantLock();
    private static final ReentrantLock returnLock = new ReentrantLock();
    private final Semaphore semaphore;
    private final Queue<ProxyConnection> availableConnections;

    ConnectionPoolManager() {
        availableConnections = new ArrayDeque<>(DEFAULT_CONNECTION_POOL_SIZE);
        semaphore = new Semaphore(DEFAULT_CONNECTION_POOL_SIZE);
        initializeConnectionPool();
    }

    void initializeConnectionPool() {
        LOG.debug("Starting ConnectionPoolManager initialization");
        for (int connectionIndex = 0; connectionIndex < DEFAULT_CONNECTION_POOL_SIZE; connectionIndex++) {
            ProxyConnection proxyConnection = new ProxyConnection(ConnectionCreator.create());
            availableConnections.add(proxyConnection);
        }
        LOG.debug("ConnectionPoolManager has been initialized");
    }

    public Connection getConnection() {
        try {
            LOG.debug("Getting connection from pool ...");
            getLock.lock();
            semaphore.acquire();
            return availableConnections.poll();
        } catch (InterruptedException e) {
            String errorMessage = "Error while getting connection from the pool";
            LOG.error(errorMessage);
            throw new ConnectionPoolException(errorMessage, e);
        } finally {
            getLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        LOG.debug("Returning connection into the pool ...");
        if (!(connection instanceof ProxyConnection)){
            String errorMessage = "Connection you are trying to return does not belong to the current connection poll";
            LOG.error(errorMessage);
            throw new ConnectionPoolException(errorMessage);
        } else {
            try {
                returnLock.lock();
                if (!availableConnections.contains(connection)) {
                    availableConnections.offer((ProxyConnection) connection);
                    semaphore.release();
                }
            } finally {
                returnLock.unlock();
            }
        }
    }

    public void destroyPool() {
        LOG.debug("Destroying connection pool ...");
        for (int connectionIndex = 0; connectionIndex < DEFAULT_CONNECTION_POOL_SIZE; connectionIndex++) {
            try {
                Objects.requireNonNull(availableConnections.poll()).reallyClose();
            } catch (SQLException e) {
                String errorMessage = "Error during destroying connection pool";
                LOG.error(errorMessage);
                throw new ConnectionPoolException(errorMessage);
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                String errorMessage = "Error when deregistration drivers";
                LOG.error(errorMessage);
                throw new ConnectionPoolException(errorMessage, e);
            }
        });
    }
}


