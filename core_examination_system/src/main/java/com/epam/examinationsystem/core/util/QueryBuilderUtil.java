package com.epam.examinationsystem.core.util;

import com.epam.examinationsystem.core.entity.AbstractEntity;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class QueryBuilderUtil {

    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

    private QueryBuilderUtil() {
    }

    public static String generateFindByUuidQuery(String tableName, UUID uuid) {
        return "SELECT * FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String generateFindByPropertyQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT * FROM " + tableName + " WHERE " + propertyName + " = " + propertyValue + ";";
    }

    public static String generateCountByUuidQuery(String tableName, UUID uuid) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String generateFindAllQuery(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }





    //------------------------------------------------------------------------------------------------------------------







    public static String generateInsertQuery(String tableName, int countOfColumns) {
        return "INSERT INTO " + tableName + " VALUES(default,default" + ",?".repeat(Math.max(0, countOfColumns)) + ");";
    }

    public static String generateDeleteQuery(String tableName, String columnName, String predicate) {
        if (isValidUUID(predicate)) {
            UUID uuid = UUID.fromString(predicate);
            return "DELETE FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
        }
        return "DELETE FROM " + tableName + " WHERE " + columnName + " = " + predicate;
    }

    public static String generateDeleteQuery(String tableName, Map<String, String> columnWithValue) {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(tableName);
        query.append(" WHERE ");
        for (Map.Entry<String, String> entry : columnWithValue.entrySet()) {
            query
                    .append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append(" and ");
        }
        query.delete(query.length() - 5, query.length() - 1);
        return query.toString();
    }

    public static String generateCountQuery(String tableName) {
        return "SELECT COUNT(*) as count FROM " + tableName;
    }


//    public static <E extends AbstractEntity> String generateUpdateQueryByUuid(String tableName, Class<E> clazz, UUID uuid, String[] excludeFields) {
//        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET");
//        List<String> columns = getFieldNames(clazz);
//        for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++) {
//            String currentColumn = columns.get(columnIndex);
//            Predicate<String> predicate = s -> !s.equals(currentColumn);
//            boolean isValid = Arrays.stream(excludeFields).allMatch(predicate);
//            if (!currentColumn.equals("id") && isValid) {
//                query
//                        .append(" ")
//                        .append(fromCamelCaseToSnakeCase(currentColumn))
//                        .append(" = ?");
//                if (columnIndex != columns.size() - 1 - excludeFields.length) {
//                    query.append(",");
//                }
//            }
//        }
//        query
//                .append(" WHERE uuid = ")
//                .append('\'')
//                .append(uuid)
//                .append('\'')
//                .append(";");

//        return query.toString();

//    }
//    public static String generateFindAllWithParamsQuery(String tableName, DataTableRequest request) {
//        int offset = (request.getCurrentPage() - 1) * request.getPageSize();
//        return "SELECT * FROM " + tableName + " ORDER BY " + fromCamelCaseToSnakeCase(request.getSort()) + " " +
//                request.getOrder().toLowerCase() + " LIMIT " + request.getPageSize() +
//                " OFFSET " + offset + ";";

//    }

    public static String generateFindByUuidsQuery(List<UUID> uuids, String tableName) {
        StringBuilder query = new StringBuilder("SELECT * FROM " + tableName + " WHERE uuid IN (");
        for (int uuidIndex = 0; uuidIndex < uuids.size(); uuidIndex++) {
            query.append('\'');
            query.append(uuids.get(uuidIndex));
            query.append('\'');
            if (uuidIndex != uuids.size() - 1) {
                query.append(",");
            }
        }
        query.append(");");
        return query.toString();
    }

    public static <E extends AbstractEntity> String generateSecondEntityFindQueryByFirstEntityIdOnAdjacentTable(
            Long firstEntityId,
            Class<E> secondClazz,
            String firstTableName,
            String secondTableName,
            String adjacentTableName
    ) {
        StringBuilder query = new StringBuilder("SELECT");
        List<String> columns = getFieldNames(secondClazz);
        for (String column : columns) {
            if (!column.equals(fromSnakeCaseToCamelCase(firstTableName + 's'))) {
                query
                        .append(" ")
                        .append(fromCamelCaseToSnakeCase(column))
                        .append(',');
            }
        }
        query.setLength(query.length() - 1);
        query
                .append(" FROM ")
                .append(secondTableName)
                .append(" JOIN ")
                .append(adjacentTableName)
                .append(" ON ")
                .append(secondTableName)
                .append(".id = ")
                .append(adjacentTableName)
                .append('.')
                .append(secondTableName)
                .append("_id")
                .append(" WHERE ")
                .append(firstTableName)
                .append("_id = ")
                .append(firstEntityId)
                .append(';');
        return query.toString();
    }


    private static <E extends AbstractEntity> List<String> getFieldNames(Class<E> clazz) {
        Field[] entityFields = clazz.getDeclaredFields();
        Field[] baseEntityFields = clazz.getSuperclass().getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : baseEntityFields) {
            fieldNames.add(field.getName());
        }
        for (Field field : entityFields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    private static String fromCamelCaseToSnakeCase(String field) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return field.replaceAll(regex, replacement).toLowerCase();
    }

    private static String fromSnakeCaseToCamelCase(String field) {
        return Pattern.compile("_([a-z])")
                .matcher(field)
                .replaceAll(m -> m.group(1).toUpperCase());
    }

    public static boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }

}
