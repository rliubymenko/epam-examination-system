package com.epam.di.connectionpool;

import com.epam.di.environment.PropertyResolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final String DRIVER_CLASS_NAME;
    private static final PropertyResolver PROPERTY_RESOLVER;

    static {
        PROPERTY_RESOLVER = PropertyResolver.getInstance();
        URL = PROPERTY_RESOLVER.getProperty("db.url");
        USERNAME = PROPERTY_RESOLVER.getProperty("db.username");
        PASSWORD = PROPERTY_RESOLVER.getProperty("db.password");
        DRIVER_CLASS_NAME = PROPERTY_RESOLVER.getProperty("db.driver-class-name");
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Driver is not found " + e.getMessage());
        }
    }

    public static Connection create() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
