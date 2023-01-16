package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.UserTestDao;
import com.epam.examinationsystem.core.dao.common.TransactionManager;
import com.epam.examinationsystem.core.entity.UserTest;
import com.epam.examinationsystem.core.service.UserTestService;

@PleaseService
public class UserTestServiceImpl implements UserTestService {

    @PleaseInject
    private UserTestDao userTestDao;

    @PleaseInject
    private TransactionManager<UserTest> transactionManager;
}
