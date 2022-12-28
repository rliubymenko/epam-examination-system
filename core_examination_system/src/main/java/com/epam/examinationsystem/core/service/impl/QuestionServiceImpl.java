package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.service.QuestionService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class QuestionServiceImpl implements QuestionService {

    private QuestionDao questionDao;

    @PleasePostConstruct
    private void init() {
        questionDao =  ObjectProvider.getInstance(QuestionDao.class);
    }
}
