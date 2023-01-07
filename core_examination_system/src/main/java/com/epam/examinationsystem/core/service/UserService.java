package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.dto.UserDto;
import com.epam.examinationsystem.core.exception.ServiceException;

import java.util.Optional;

public interface UserService {

    boolean createStudent(UserDto userDto) throws ServiceException;

    Optional<UserDto> findByUsername(String username) throws ServiceException;

    boolean existsByUsername(String username) throws ServiceException;

    boolean existsByEmail(String email) throws ServiceException;
}
