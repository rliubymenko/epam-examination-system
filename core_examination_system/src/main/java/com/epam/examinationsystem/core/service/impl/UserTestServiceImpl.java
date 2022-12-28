package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.service.UserTestService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class UserTestServiceImpl implements UserTestService {

    private UserTestDao userTestDao;

    @PleasePostConstruct
    private void init() {
        userTestDao = ObjectProvider.getInstance(UserTestDao.class);
    }
}
