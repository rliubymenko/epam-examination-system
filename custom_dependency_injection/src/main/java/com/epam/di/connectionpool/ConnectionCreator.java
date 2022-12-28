package com.epam.di.connectionpool;

import com.epam.di.environment.PropertyResolver;
import com.epam.di.exception.DatabasePropertyAbsenceException;
import com.epam.di.util.PropertyValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public class ConnectionCreator {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionCreator.class);

    private ConnectionCreator() {
    }

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
        Map<String, String> toValidateMap = formUpValidateMap();
        String message = PropertyValidatorUtil.validate(toValidateMap);
        if (message != null) {
            LOG.error(message);
            throw new DatabasePropertyAbsenceException(message);
        }
        setUpDatabaseDriver();
    }

    public static Connection create() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    private static Map<String, String> formUpValidateMap() {
        Map<String, String> toValidateMap = new HashMap<>();
        toValidateMap.put("db.url", URL);
        toValidateMap.put("db.username", USERNAME);
        toValidateMap.put("db.password", PASSWORD);
        toValidateMap.put("db.driver-class-name", DRIVER_CLASS_NAME);
        return toValidateMap;
    }

    private static void setUpDatabaseDriver() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            String message = MessageFormat.format("Driver {0} is not found", DRIVER_CLASS_NAME);
            LOG.error(message);
            throw new IllegalArgumentException(message, e);
        }
    }
}
