package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.List;
import java.util.UUID;

public interface AnswerDao extends CommonDao<Answer> {

    List<Answer> findAllByQuestionUuid(UUID uuid) throws DaoException;
}
