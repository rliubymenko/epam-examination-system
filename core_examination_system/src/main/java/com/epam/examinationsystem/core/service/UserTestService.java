package com.epam.examinationsystem.core.service;

import com.epam.examinationsystem.core.dto.UserTestDto;
import com.epam.examinationsystem.core.exception.ServiceException;

public interface UserTestService {

    boolean create(UserTestDto userTestDto) throws ServiceException;
}
