package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.entity.Role;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.exception.ServiceException;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.examinationsystem.core.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            return userDao.findByEmail(email).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }

    public DataTableResponse<UserDto> findAll(DataTableRequest request) throws ServiceException {
        LOG.debug("Find all users by {}", request);
        transactionManager.beginWithAutoCommit(userDao, roleDao);
        try {
            List<User> users = userDao.findAll(request);
            long count = userDao.count();
            long entriesFrom = ((long) (request.getCurrentPage() - 1) * request.getPageSize()) + 1;
            long entriesTo = Math.min((long) request.getCurrentPage() * request.getPageSize(), count);
            long totalPageSize;
            if (count % request.getPageSize() == 0) {
                totalPageSize = count / request.getPageSize();
            } else {
                totalPageSize = count / request.getPageSize() + 1;
            }
            List<UserDto> userDtos = users.stream()
                    .map(UserDto.builder()::fromUser)
                    .toList();
            DataTableResponse<UserDto> response = new DataTableResponse<>();
            response.setDtos(userDtos);
            response.setEntriesFrom(entriesFrom);
            response.setEntriesTo(entriesTo);
            response.setTotalPageSize(totalPageSize);
            response.setEntitiesSize(count);
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
            boolean isCreated = userDao.create(user);
            transactionManager.commit();
            return isCreated;
        } catch (DaoException e) {
            transactionManager.rollback();
            throw new ServiceException(e);
        } finally {
            transactionManager.end();
        }
    }
}
