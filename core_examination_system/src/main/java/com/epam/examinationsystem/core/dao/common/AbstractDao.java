package com.epam.examinationsystem.core.dao.common;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractDao<ENTITY extends AbstractEntity> implements CommonDao<ENTITY> {

    private final Logger log;
    private final String entityName;
    private final String tableName;
    protected Connection connection;

    protected AbstractDao(Logger log, String entityName, String tableName) {
        this.log = log;
        this.entityName = entityName;
        this.tableName = tableName;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<ENTITY> findByUuid(UUID uuid) throws DaoException {
        Optional<ENTITY> maybeEntity;
        LoggerUtil.findByUuidStartLogging(log, entityName, uuid);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByUuidQuery(tableName, uuid);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                ENTITY entity = extractEntity(resultSet);
                maybeEntity = Optional.ofNullable(entity);
            }
        } catch (SQLException | DaoException e) {
            String message = LoggerUtil.findByUuidErrorLogging(log, entityName, uuid);
            throw new DaoException(message, e);
        }
        return maybeEntity;
    }

    @Override
    public ENTITY getById(Long id) throws DaoException {
        ENTITY entity;
        LoggerUtil.findByIdStartLogging(log, entityName, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.generateFindByIdQuery(tableName, id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                entity = extractEntity(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(log, entityName, id);
            throw new DaoException(message, e);
        }
        return entity;
    }

    @Override
    public boolean existsByUuid(UUID uuid) throws DaoException {
        long count = 0;
        LoggerUtil.existByUuidStartLogging(log, entityName, uuid);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.generateCountByUuidQuery(tableName, uuid);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    count = resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.existByUuidErrorLogging(log, entityName, uuid);
            throw new DaoException(message, e);
        }
        return count == 1;
    }

    @Override
    public List<ENTITY> findAll() throws DaoException {
        List<ENTITY> entities;
        LoggerUtil.findAllStartLogging(log, entityName);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.generateFindAllQuery(tableName);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllErrorLogging(log, entityName);
            throw new DaoException(message, e);
        }
        return entities;
    }

    @Override
    public List<ENTITY> findAll(DataTableRequest request) throws DaoException {
        List<ENTITY> entities;
        LoggerUtil.findAllWithParametersStartLogging(log, entityName, request);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.generateFindAllWithParametersQuery(tableName, request);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllWithParametersErrorLogging(log, entityName, request);
            throw new DaoException(message, e);
        }
        return entities;
    }

    @Override
    public ENTITY create(ENTITY entity) throws DaoException {
        LoggerUtil.createEntityStartLogging(log, entityName);
        String insertQuery = getInsertQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            populateInsertQuery(preparedStatement, entity);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long lastGeneratedId = resultSet.getLong(1);
            return getById(lastGeneratedId);
        } catch (SQLException e) {
            String message = LoggerUtil.createEntityErrorLogging(log, entityName);
            throw new DaoException(message, e);
        }
    }

    @Override
    public ENTITY update(ENTITY entity) throws DaoException {
        LoggerUtil.updateEntityStartLogging(log, entityName, entity.getUuid());
        String updateQuery = getUpdateQuery(entity);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            populateUpdateQuery(preparedStatement, entity);
            preparedStatement.executeUpdate();
            return findByUuid(entity.getUuid()).get();
        } catch (SQLException e) {
            String message = LoggerUtil.updateEntityErrorLogging(log, entityName, entity.getUuid());
            throw new DaoException(message, e);
        }
    }

    @Override
    public boolean deleteByUuid(UUID uuid) throws DaoException {
        LoggerUtil.deleteByUuidStartLogging(log, entityName, uuid);
        String deleteQuery = QueryBuilderUtil.generateDeleteQuery(tableName, "uuid", uuid.toString());
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.deleteByUuidErrorLogging(log, entityName, uuid);
            throw new DaoException(message, e);
        }
    }

    @Override
    public long count() throws DaoException {
        LoggerUtil.countingRecordsStartLogging(log, entityName);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.generateCountQuery(tableName);
            try (ResultSet resultSet = statement.executeQuery(countQuery)) {
                if (resultSet.next()) {
                    return resultSet.getLong("count");
                }
            }
        } catch (SQLException e) {
            String message = LoggerUtil.countingRecordsErrorLogging(log, entityName);
            throw new DaoException(message, e);
        }
        return 0;
    }

    public abstract ENTITY extractEntity(ResultSet resultSet) throws SQLException, DaoException;

    public abstract List<ENTITY> extractEntities(ResultSet resultSet) throws SQLException, DaoException;

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery(ENTITY entity);

    public abstract void populateInsertQuery(PreparedStatement preparedStatement, ENTITY entity) throws SQLException;

    public abstract void populateUpdateQuery(PreparedStatement preparedStatement, ENTITY entity) throws SQLException;
}
