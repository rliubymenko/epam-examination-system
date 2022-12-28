package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.RoleDao;
import com.epam.examinationsystem.core.service.RoleService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @PleasePostConstruct
    private void init() {
        roleDao = ObjectProvider.getInstance(RoleDao.class);
    }
}
