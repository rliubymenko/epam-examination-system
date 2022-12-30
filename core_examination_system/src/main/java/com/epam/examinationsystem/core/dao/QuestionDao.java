package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.exception.DaoException;

public interface QuestionDao extends CommonDao<Question> {

    Question getById(Long id) throws DaoException;
}
