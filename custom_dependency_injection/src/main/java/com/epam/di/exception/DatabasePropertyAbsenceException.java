package com.epam.di.exception;

public class DatabasePropertyAbsenceException extends RuntimeException {

    public DatabasePropertyAbsenceException(String message) {
        super(message);
    }
}
