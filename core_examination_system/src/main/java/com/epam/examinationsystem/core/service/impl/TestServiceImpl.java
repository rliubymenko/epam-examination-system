package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.service.TestService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class TestServiceImpl implements TestService {

    private TestDao testDao;

    @PleasePostConstruct
    private void init() {
        testDao = ObjectProvider.getInstance(TestDao.class);
    }
}
