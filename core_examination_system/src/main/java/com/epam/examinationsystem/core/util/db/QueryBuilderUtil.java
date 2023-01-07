package com.epam.examinationsystem.core.util.db;

import com.epam.examinationsystem.core.entity.AbstractEntity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class QueryBuilderUtil {

    private static final Pattern UUID_REGEX_PATTERN = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

    private QueryBuilderUtil() {
    }

    public static String generateFindByUuidQuery(String tableName, UUID uuid) {
        return "SELECT * FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String generateFindByIdQuery(String tableName, Long id) {
        return "SELECT * FROM " + tableName + " WHERE id = " + id + ";";
    }

    public static String generateFindByQuery(String tableName, String propertyName, String propertyValue, boolean isWrapped) {
        if (isWrapped) {
            return "SELECT * FROM " + tableName + " WHERE " + propertyName + " = " + '\'' + propertyValue + '\'' + ";";
        }
        return "SELECT * FROM " + tableName + " WHERE " + propertyName + " = " + propertyValue + ";";
    }

    public static String generateCountQuery(String tableName) {
        return "SELECT COUNT(*) as count FROM " + tableName + ";";
    }

    public static String generateCountByUuidQuery(String tableName, UUID uuid) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String generateCountByQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE " + propertyName + " = " + propertyValue + ";";
    }

    public static String generateFindAllQuery(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }

    public static String generateDeleteQuery(String tableName, String columnName, String predicate) {
        if (isValidUUID(predicate)) {
            UUID uuid = UUID.fromString(predicate);
            return "DELETE FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
        }
        return "DELETE FROM " + tableName + " WHERE " + columnName + " = " + predicate + ";";
    }

    public static String generateInsertQuery(String tableName, int countOfColumns) {
        return "INSERT INTO " + tableName + " VALUES(default,default" + ",?".repeat(Math.max(0, countOfColumns)) + ");";
    }

    public static String generateUpdateQueryByUuid(String tableName, UUID uuid, List<String> columnNames) {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET");
        for (int columnIndex = 0; columnIndex < columnNames.size(); columnIndex++) {
            String currentColumn = columnNames.get(columnIndex);
            if (!currentColumn.equals("id") && !currentColumn.equals("uuid")) {
                query
                        .append(" ")
                        .append(currentColumn)
                        .append(" = ?");
                if (columnIndex != columnNames.size() - 1) {
                    query.append(",");
                }
            }
        }
        query
                .append(" WHERE uuid = ")
                .append('\'')
                .append(uuid)
                .append('\'')
                .append(";");
        return query.toString();
    }

    public static void populatePreparedStatement(PreparedStatement preparedStatement, Object... fields) throws SQLException {
        for (int index = 0; index < fields.length; index++) {
            populatePreparedStatement(preparedStatement, fields[index], index + 1);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    private static boolean isValidUUID(String str) {
        if (str == null) {
            return false;
        }
        return UUID_REGEX_PATTERN.matcher(str).matches();
    }

    private static void populatePreparedStatement(PreparedStatement preparedStatement, Object field, int index) throws SQLException {
        String type = field.getClass().getSimpleName();
        if (field.getClass().isAssignableFrom(AbstractEntity.class)) {
            preparedStatement.setLong(index, ((AbstractEntity) field).getId());
        }
        if (field.getClass().isAssignableFrom(Enum.class)) {
            preparedStatement.setString(index, field.toString());
        }
        switch (type) {
            case "String" -> preparedStatement.setString(index, field.toString());
            case "Boolean" -> preparedStatement.setBoolean(index, (Boolean) field);
            case "Short" -> preparedStatement.setShort(index, (Short) field);
            case "Integer" -> preparedStatement.setInt(index, (Integer) field);
            case "Long" -> preparedStatement.setLong(index, (Long) field);
            case "Float" -> preparedStatement.setFloat(index, (Float) field);
            case "Double" -> preparedStatement.setDouble(index, (Double) field);
            case "LocalDate" -> preparedStatement.setTimestamp(index, Timestamp.valueOf((
                    (LocalDate) field).atStartOfDay()
            ));
            case "LocalDateTime" -> preparedStatement.setTimestamp(index, Timestamp.valueOf((LocalDateTime) field));
            case "UUID" -> preparedStatement.setObject(index, (UUID) field, Types.OTHER);
            default -> preparedStatement.setObject(index, field.toString(), Types.OTHER);
        }
    }
}
