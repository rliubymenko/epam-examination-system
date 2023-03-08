package com.epam.examinationsystem.core.dao.common;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * The abstract dao class that contains common CRUD operations.
 */
public abstract class AbstractDao<ENTITY extends AbstractEntity> implements CommonDao<ENTITY> {

    private final Logger log;
    private final String entityName;
    private final String tableName;
    private final Map<String, String> foreignTableNamesWithKeys;
    protected Connection connection;

    protected AbstractDao(Logger log, String entityName, String tableName, Map<String, String> foreignTableNamesWithKeys) {
        this.log = log;
        this.entityName = entityName;
        this.tableName = tableName;
        this.foreignTableNamesWithKeys = foreignTableNamesWithKeys;
    }

    /**
     * The method to inject a connection to a concrete dao.
     *
     * @param connection the connection to be injected.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * The method to search entity by uuid.
     *
     * @param uuid the uuid of searchable record.
     * @return <code>Optional</code> an Optional with the entity.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> or a <code>DaoException</code> is occurred.
     */
    @Override
    public Optional<ENTITY> findByUuid(UUID uuid) throws DaoException {
        Optional<ENTITY> maybeEntity;
        LoggerUtil.findByUuidStartLogging(log, entityName, uuid);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByUuidQuery(tableName, uuid);
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

    /**
     * The method to search entity by id.
     *
     * @param id the id of searchable record.
     * @return a descendant of <code>AbstractEntity</code>.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public ENTITY getById(Long id) throws DaoException {
        ENTITY entity;
        LoggerUtil.findByIdStartLogging(log, entityName, id);
        try (Statement statement = connection.createStatement()) {
            String findQuery = QueryBuilderUtil.findByIdQuery(tableName, id);
            try (ResultSet resultSet = statement.executeQuery(findQuery)) {
                entity = extractEntity(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findByIdErrorLogging(log, entityName, id);
            throw new DaoException(message, e);
        }
        return entity;
    }

    /**
     * The method for checking the existence of the entity by given uuid.
     *
     * @param uuid the uuid of searchable record.
     * @return true - if record exists by given uuid or false otherwise.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public boolean existsByUuid(UUID uuid) throws DaoException {
        long count = 0;
        LoggerUtil.existByUuidStartLogging(log, entityName, uuid);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.countByUuidQuery(tableName, uuid);
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

    /**
     * The method for searching all entities.
     *
     * @return a <code>List</code> with all entities.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public List<ENTITY> findAll() throws DaoException {
        List<ENTITY> entities;
        LoggerUtil.findAllStartLogging(log, entityName);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.findAllQuery(tableName);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllErrorLogging(log, entityName);
            throw new DaoException(message, e);
        }
        return entities;
    }

    /**
     * The method for searching all entities by parameters for the table representation.
     *
     * @param request the DataTableRequest instance.
     * @return a <code>List</code> with all entities.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public List<ENTITY> findAll(DataTableRequest request) throws DaoException {
        List<ENTITY> entities;
        LoggerUtil.findAllByParametersStartLogging(log, entityName, request);
        try (Statement statement = connection.createStatement()) {
            String findAllQuery = QueryBuilderUtil.findAllAndJoinTableByForeignKeyByQueryParameters(tableName, request, foreignTableNamesWithKeys);
            try (ResultSet resultSet = statement.executeQuery(findAllQuery)) {
                entities = extractEntities(resultSet);
            }
        } catch (SQLException e) {
            String message = LoggerUtil.findAllByParametersErrorLogging(log, entityName, request);
            throw new DaoException(message, e);
        }
        return entities;
    }

    /**
     * The method for creating the entity.
     *
     * @param entity the given entity that is a descendant of AbstractEntity.
     * @return the created entity.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
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

    /**
     * The method for updating the entity.
     *
     * @param entity the given entity that is a descendant of AbstractEntity.
     * @return the updated entity.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
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

    /**
     * The method for deleting the entity.
     *
     * @param uuid an uuid of the deletion candidate.
     * @return true - if the deletion was successful, false otherwise.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public boolean deleteByUuid(UUID uuid) throws DaoException {
        LoggerUtil.deleteByUuidStartLogging(log, entityName, uuid);
        String deleteQuery = QueryBuilderUtil.deleteQuery(tableName, "uuid", uuid.toString());
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            String message = LoggerUtil.deleteByUuidErrorLogging(log, entityName, uuid);
            throw new DaoException(message, e);
        }
    }

    /**
     * The method for counting the entity number for the request params.
     *
     * @param request the DataTableRequest instance.
     * @return a count of existing entity that corresponds to the request params.
     * @throws DaoException throws a <code>DaoException</code> if a <code>SQLException</code> is occurred.
     */
    @Override
    public long count(DataTableRequest request) throws DaoException {
        LoggerUtil.countingRecordsStartLogging(log, entityName);
        try (Statement statement = connection.createStatement()) {
            String countQuery = QueryBuilderUtil.countQuery(tableName, request, foreignTableNamesWithKeys);
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

    /**
     * The abstract method for extracting entity from ResultSet.
     *
     * @param resultSet a ResultSet.
     * @return the extracted entity.
     */
    public abstract ENTITY extractEntity(ResultSet resultSet) throws SQLException, DaoException;

    /**
     * The abstract method for extracting entities from ResultSet.
     *
     * @param resultSet a ResultSet.
     * @return the <code>List</code> with extracted entities.
     */
    public abstract List<ENTITY> extractEntities(ResultSet resultSet) throws SQLException, DaoException;

    /**
     * The abstract method for getting insert SQL query for saving entity.
     *
     * @return the insert query.
     */
    public abstract String getInsertQuery();

    /**
     * The abstract method for getting update SQL query for updating entity.
     *
     * @return the update query.
     */
    public abstract String getUpdateQuery(ENTITY entity);

    /**
     * The abstract method for populating insert a SQL query by fitting the necessary gaps.
     */
    public abstract void populateInsertQuery(PreparedStatement preparedStatement, ENTITY entity) throws SQLException;

    /**
     * The abstract method for populating update a SQL query by fitting the necessary gaps.
     */
    public abstract void populateUpdateQuery(PreparedStatement preparedStatement, ENTITY entity) throws SQLException;
}
