package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.UserDao;
import com.epam.examinationsystem.core.service.UserService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @PleasePostConstruct
    private void init() {
        userDao = ObjectProvider.getInstance(UserDao.class);
    }
}
