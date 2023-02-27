package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.entity.AbstractEntity;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.PasswordEncoder;
import com.epam.examinationsystem.core.util.validation.ParameterValidator;
import com.epam.examinationsystem.core.util.web.PageableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@PleaseService
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @PleaseInject
    private UserDao userDao;

    @PleaseInject
    private RoleDao roleDao;

    @PleaseInject
    private TransactionManager<User> transactionManager;

    @Override
    public Optional<UserDto> findByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Find user by uuid {}", uuid);
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            Optional<User> maybeUser = userDao.findByUuid(uuid);
            return maybeUser.map(UserDto.builder()::fromUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public Optional<UserDto> findByUsername(String username) throws ServiceException {
        LOG.debug("Find user by username {}", username);
        if (username == null) {
            return Optional.empty();
        }
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            Optional<User> maybeUser = userDao.findByUsername(username);
            return maybeUser.map(UserDto.builder()::fromUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean existsByUsername(String username) throws ServiceException {
        LOG.debug("Check if exists by username {}", username);
        if (username == null) {
            return false;
        }
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            return userDao.findByUsername(username).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean existsByEmail(String email) throws ServiceException {
        LOG.debug("Check if exists by email {}", email);
        if (email == null) {
            return false;
        }
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            return userDao.findByEmail(email).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean existsByUuid(UUID uuid) throws ServiceException {
        LOG.debug("Check if exists by uuid {}", uuid);
        if (uuid == null) {
            return false;
        }
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            return userDao.existsByUuid(uuid);
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public List<UserDto> findAll() throws ServiceException {
        LOG.debug("Find all users");
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            return userDao.findAll()
                    .stream()
                    .map(UserDto.builder()::fromUser)
                    .toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public DataTableResponse<UserDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all users by {}", request);
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            List<User> users = userDao.findAll(request);
            Map<UUID, String> rolesForSearch = getRolesForSearch();
            long countOfEntities = userDao.count(request);
            DataTableResponse<UserDto> response = PageableUtil.calculatePageableData(request, countOfEntities);
            List<UserDto> userDtos = users.stream()
                    .map(UserDto.builder()::fromUser)
                    .toList();
            Map<UUID, String> currentRoleForSearch = getCurrentRoleForSearch(request.getSearchUuid());
            response.setCurrentDataForSearch(currentRoleForSearch);
            response.setDataForSearch(List.of(rolesForSearch));
            response.setDtos(userDtos);
            return response;
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean createStudent(UserDto userDto) throws ServiceException {
        LOG.debug("Creation user from user dto {}", userDto);
        transactionManager.begin(userDao, roleDao);
        try {
            Role studentRole = roleDao.findByUserType(UserType.STUDENT).get();
            User user = User.builder()
                    .setUsername(userDto.getUsername())
                    .setEmail(userDto.getEmail())
                    .setPassword(PasswordEncoder.encrypt(userDto.getPassword()))
                    .setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setIsActivated(true)
                    .setRole(studentRole)
                    .build();
            userDao.create(user);
            transactionManager.commit();
            return true;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean resetPassword(UserDto userDto, String newPassword) throws ServiceException {
        LOG.debug("Resetting password for user {}", userDto);
        transactionManager.begin(userDao, roleDao);
        try {
            UUID currentUserUuid = UUID.fromString(userDto.getUuid());
            userDao.resetPassword(currentUserUuid, PasswordEncoder.encrypt(newPassword));
            transactionManager.commit();
            return true;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    @Override
    public boolean update(UserDto userDto, boolean isLoggedUser) throws ServiceException {
        LOG.debug("Updating user by user dto {}", userDto);
        transactionManager.begin(userDao, roleDao);
        try {
            boolean isUpdated = false;
            UUID uuid = UUID.fromString(userDto.getUuid());
            Optional<User> maybeUser = userDao.findByUuid(uuid);
            if (maybeUser.isPresent()) {
                User.UserBuilder userBuilder = User.builder()
                        .setUuid(maybeUser.get().getUuid())
                        .setUsername(userDto.getUsername())
                        .setEmail(userDto.getEmail())
                        .setFirstName(userDto.getFirstName())
                        .setLastName(userDto.getLastName());
                if (isLoggedUser) {
                    userDao.updateWithoutChangingActivation(userBuilder.build());
                } else {
                    userBuilder.setIsActivated(userDto.getIsActivated());
                    userDao.update(userBuilder.build());
                }
                isUpdated = true;
            }
            transactionManager.commit();
            return isUpdated;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    private Map<UUID, String> getRolesForSearch() throws DaoException {
        return roleDao.findAll()
                .stream()
                .collect(Collectors.toMap(
                        AbstractEntity::getUuid,
                        role -> role.getName().toString()
                ));
    }

    private Map<UUID, String> getCurrentRoleForSearch(String roleUuid) throws DaoException {
        Map<UUID, String> currentRoleForSearch = new HashMap<>();
        if (ParameterValidator.isValidUUID(roleUuid) && roleDao.existsByUuid(UUID.fromString(roleUuid))) {
            currentRoleForSearch = roleDao.findByUuid(UUID.fromString(roleUuid))
                    .stream()
                    .collect(Collectors.toMap(
                            AbstractEntity::getUuid,
                            role -> role.getName().toString()
                    ));
        }
        return currentRoleForSearch;
    }
}
