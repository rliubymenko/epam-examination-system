package com.epam.examinationsystem.core.util.db;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class QueryBuilderUtil {

    private QueryBuilderUtil() {
    }

    public static String findByUuidQuery(String tableName, UUID uuid) {
        return "SELECT * FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String findByIdQuery(String tableName, Long id) {
        return "SELECT * FROM " + tableName + " WHERE id = " + id + ";";
    }

    public static String findByQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT * FROM " + tableName + " WHERE " + propertyName + " = " + propertyValue + ";";
    }

    public static String findByWrappedValueQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT * FROM " + tableName + " WHERE " + propertyName + " = " + '\'' + propertyValue + '\'' + ";";
    }

    public static String countQuery(String tableName) {
        return "SELECT COUNT(*) as count FROM " + tableName + ";";
    }

    public static String countByUuidQuery(String tableName, UUID uuid) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
    }

    public static String countByQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE " + propertyName + " = " + propertyValue + ";";
    }

    public static String countByWrappedValueQuery(String tableName, String propertyName, String propertyValue) {
        return "SELECT COUNT(*) as count FROM " + tableName + " WHERE " + propertyName + " = " + '\'' + propertyValue + '\'' + ";";
    }

    public static String findAllByPredicates(String tableName, Map<String, String> columnWithPredicates) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        query.append(tableName);
        query.append(" WHERE ");
        for (Map.Entry<String, String> entry : columnWithPredicates.entrySet()) {
            query
                    .append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append(" and ");
        }
        return query
                .delete(query.length() - 5, query.length() - 1)
                .append(";")
                .toString();
    }

    public static String findAllQuery(String tableName) {
        return "SELECT * FROM " + tableName + ";";
    }

    public static String findAllWithParametersQuery(String tableName, DataTableRequest request) {
        int offset = (request.getCurrentPage() - 1) * request.getPageSize();
        return new StringBuilder()
                .append("SELECT * FROM ")
                .append(tableName)
                .append(" ORDER BY ")
                .append(fromCamelCaseToSnakeCase(request.getSort()))
                .append(" ")
                .append(request.getOrder().toLowerCase())
                .append(" LIMIT ")
                .append(request.getPageSize())
                .append(" OFFSET ")
                .append(offset)
                .append(";").toString();
    }

    public static String deleteQuery(String tableName, String columnName, String predicate) {
        if (ParameterValidator.isValidUUID(predicate)) {
            UUID uuid = UUID.fromString(predicate);
            return "DELETE FROM " + tableName + " WHERE uuid = " + '\'' + uuid + '\'' + ";";
        }
        return "DELETE FROM " + tableName + " WHERE " + columnName + " = " + predicate + ";";
    }

    public static String insertQuery(String tableName, int countOfColumns) {
        return "INSERT INTO " + tableName + " VALUES(default,default" + ",?".repeat(Math.max(0, countOfColumns)) + ");";
    }

    public static String updateQueryByUuid(String tableName, UUID uuid, List<String> columnNames) {
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

    private static String fromCamelCaseToSnakeCase(String field) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return field.replaceAll(regex, replacement).toLowerCase();
    }

    private static void populatePreparedStatement(PreparedStatement preparedStatement, Object field, int index) throws SQLException {
        if (field == null) {
            preparedStatement.setNull(index, Types.OTHER);
        } else {
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
}
