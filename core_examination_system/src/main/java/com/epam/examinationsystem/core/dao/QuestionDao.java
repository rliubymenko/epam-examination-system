package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.List;
import java.util.UUID;

public interface QuestionDao extends CommonDao<Question> {

    List<Question> findAllByTestUuid(UUID uuid) throws DaoException;
}
