package com.epam.examinationsystem.core.util;

import com.epam.examinationsystem.core.enumeration.UserType;
import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.UUID;

public class LoggerUtil {

    private LoggerUtil() {
    }

    public static void findByUuidStartLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Finding {0} entity by uuid: {1}", entityName, uuid);
        log.debug(message);
    }

    public static String findByUuidErrorLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Error occurred while trying to find {0} by uuid: {1}", entityName, uuid);
        log.error(message);
        return message;
    }

    public static void findByStartLogging(Logger log, String entityName, String searchCriteriaName, String searchCriteriaValue) {
        String message = MessageFormat.format("Finding {0} entity by {1}: {2}", entityName, searchCriteriaName, searchCriteriaValue);
        log.debug(message);
    }

    public static String findByErrorLogging(Logger log, String entityName, String searchCriteriaName, String searchCriteriaValue) {
        String message = MessageFormat.format("Error occurred while trying to find {0} by {1}: {2}", entityName, searchCriteriaName, searchCriteriaValue);
        log.error(message);
        return message;
    }

    public static void findByIdStartLogging(Logger log, String entityName, Long id) {
        String message = MessageFormat.format("Finding {0} entity by id: {1}", entityName, id);
        log.debug(message);
    }

    public static String findByIdErrorLogging(Logger log, String entityName, Long id) {
        String message = MessageFormat.format("Error occurred while trying to find {0} by id: {1}", entityName, id);
        log.error(message);
        return message;
    }

    public static void findByUserTypeStartLogging(Logger log, String entityName, UserType userType) {
        String message = MessageFormat.format("Finding {0} entity by user type: {1}", entityName, userType);
        log.debug(message);
    }

    public static String findByUserTypeErrorLogging(Logger log, String entityName, UserType userType) {
        String message = MessageFormat.format("Error occurred while trying to find {0} by user type: {1}", entityName, userType);
        log.error(message);
        return message;
    }

    public static void existByUuidStartLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Checking if {0} entity exists by uuid: {1}", entityName, uuid);
        log.debug(message);
    }

    public static String existByUuidErrorLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Error occurred while checking if {0} entity exists by uuid: {1}", entityName, uuid);
        log.error(message);
        return message;
    }

    public static void findAllStartLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Finding all {0} entities", entityName);
        log.debug(message);
    }

    public static String findAllErrorLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Error occurred while trying to find all {0} entities", entityName);
        log.error(message);
        return message;
    }

    public static void countingRecordsStartLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Counting the number of records in database for {0}", entityName);
        log.debug(message);
    }

    public static String countingRecordsErrorLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Error occurred while counting the number of records in database for {0}", entityName);
        log.error(message);
        return message;
    }

    public static void deleteByUuidStartLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Deleting the {0} by uuid {1}", entityName, uuid);
        log.debug(message);
    }

    public static String deleteByUuidErrorLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Error occurred while deleting the {0} by uuid {1}", entityName, uuid);
        log.error(message);
        return message;
    }

    public static void createEntityStartLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Creating entity {0}", entityName);
        log.debug(message);
    }

    public static String createEntityErrorLogging(Logger log, String entityName) {
        String message = MessageFormat.format("Error occurred while creating entity {0}", entityName);
        log.error(message);
        return message;
    }

    public static void updateEntityStartLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Updating entity {0} with uuid: {1}", entityName, uuid);
        log.debug(message);
    }

    public static String updateEntityErrorLogging(Logger log, String entityName, UUID uuid) {
        String message = MessageFormat.format("Error occurred while updating entity {0} with uuid: {1}", entityName, uuid);
        log.error(message);
        return message;
    }

    public static void existByStartLogging(Logger log, String entityName, String searchCriteriaName, String searchCriteriaValue) {
        String message = MessageFormat.format("Checking if {0} entity exists by {1}: {2}", entityName, searchCriteriaName, searchCriteriaValue);
        log.debug(message);
    }

    public static String existByErrorLogging(Logger log, String entityName, String searchCriteriaName, String searchCriteriaValue) {
        String message = MessageFormat.format("Error occurred while checking if {0} entity exists by {1}: {2}", entityName, searchCriteriaName, searchCriteriaValue);
        log.error(message);
        return message;
    }

    public static void deleteByStartLogging(Logger log, String entityName, String deleteCriteriaName, String deleteCriteriaValue) {
        String message = MessageFormat.format("Deleting the {0} by {1}: {2}", entityName, deleteCriteriaName, deleteCriteriaValue);
        log.debug(message);
    }

    public static String deleteByErrorLogging(Logger log, String entityName, String deleteCriteriaName, String deleteCriteriaValue) {
        String message = MessageFormat.format("Error occurred while deleting the {0} entity by {1}: {2}", entityName, deleteCriteriaName, deleteCriteriaValue);
        log.error(message);
        return message;
    }
}
