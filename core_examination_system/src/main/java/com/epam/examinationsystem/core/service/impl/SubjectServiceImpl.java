package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.SubjectDao;
import com.epam.examinationsystem.core.service.SubjectService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class SubjectServiceImpl implements SubjectService {

    private SubjectDao subjectDao;

    @PleasePostConstruct
    private void init() {
        subjectDao = ObjectProvider.getInstance(SubjectDao.class);
    }
}
