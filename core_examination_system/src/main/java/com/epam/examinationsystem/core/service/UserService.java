package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.datatable.DataTableRequest;
import com.epam.examinationsystem.core.datatable.DataTableResponse;
import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    boolean createStudent(UserDto userDto) throws ServiceException;

    boolean update(UserDto userDto) throws ServiceException;

    Optional<UserDto> findByUsername(String username) throws ServiceException;

    Optional<UserDto> findByUuid(UUID uuid) throws ServiceException;

    List<UserDto> findAll() throws ServiceException;

    DataTableResponse<UserDto> findAll(DataTableRequest request) throws ServiceException;

    boolean existsByUsername(String username) throws ServiceException;

    boolean existsByEmail(String email) throws ServiceException;

    boolean existsByUuid(UUID uuid) throws ServiceException;
}
