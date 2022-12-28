package com.epam.examinationsystem.core.service.impl;

import com.epam.di.annotation.PleasePostConstruct;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.service.AnswerService;
import com.epam.di.context.ObjectProvider;

@PleaseService
public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;

    @PleasePostConstruct
    private void init() {
        answerDao = ObjectProvider.getInstance(AnswerDao.class);
    }
}
