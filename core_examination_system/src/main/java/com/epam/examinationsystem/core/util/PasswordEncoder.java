package com.epam.examinationsystem.core.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordEncoder {

    private static final int SALT = 12;

    private PasswordEncoder() {
    }

    public static String encrypt(String password) {
        return password == null ? null : BCrypt.hashpw(password, BCrypt.gensalt(SALT));
    }

    public static boolean isMatched(String toCheck, String hashedPassword) {
        if (toCheck == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(toCheck, hashedPassword);
    }
}
