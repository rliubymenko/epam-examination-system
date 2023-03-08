package com.epam.examinationsystem.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Password encoder util.
 */
public class PasswordEncoder {

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

    public static String generateRandomPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true, null, new SecureRandom());
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        return pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
