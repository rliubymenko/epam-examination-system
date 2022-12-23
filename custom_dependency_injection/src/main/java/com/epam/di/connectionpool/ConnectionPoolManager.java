package com.epam.di.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionPoolManager {
    INSTANCE;

    private static final int DEFAULT_CONNECTION_POOL_SIZE = 32;
    private static final ReentrantLock getLock = new ReentrantLock();
    private static final ReentrantLock returnLock = new ReentrantLock();
    private final Semaphore semaphore;
    private final BlockingQueue<ProxyConnection> availableConnections;

    ConnectionPoolManager() {
        availableConnections = new LinkedBlockingQueue<>(DEFAULT_CONNECTION_POOL_SIZE);
        semaphore = new Semaphore(DEFAULT_CONNECTION_POOL_SIZE);
        initializeConnectionPool();
    }

    void initializeConnectionPool() {
        for (int connectionIndex = 0; connectionIndex < DEFAULT_CONNECTION_POOL_SIZE; connectionIndex++) {
            ProxyConnection proxyConnection = new ProxyConnection(ConnectionCreator.create());
            availableConnections.add(proxyConnection);
        }
    }

    public Connection getConnection() {
        try {
            getLock.lock();
            semaphore.acquire();
            return availableConnections.take();
        } catch (InterruptedException e) {
            throw new RuntimeException("Error while getting connection from pool");
        } finally {
            getLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        if (!connection.getClass().isInstance(ProxyConnection.class)) {
            throw new IllegalArgumentException("Connection you are trying to return does not belong to the current connection poll");
        } else {
            try {
                returnLock.lock();
                if (!availableConnections.contains(connection)) {
                    availableConnections.offer((ProxyConnection) connection);
                }
                semaphore.release();
            } finally {
                returnLock.unlock();
            }
        }
    }

    public void destroyPool() {
        for (int connectionIndex = 0; connectionIndex < DEFAULT_CONNECTION_POOL_SIZE; connectionIndex++) {
            try {
                availableConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException("Error during destroying connection pool " + e.getLocalizedMessage());
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException("Error when deregistration drivers");
            }
        });
    }
}


