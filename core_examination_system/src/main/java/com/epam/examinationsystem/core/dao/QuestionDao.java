package com.epam.examinationsystem.core.dao;

import com.epam.examinationsystem.core.dao.common.CommonDao;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.exception.DaoException;

import java.util.Optional;
import java.util.UUID;

public interface QuestionDao extends CommonDao<Question> {

    Optional<Question> findByTestUuid(UUID testUuid) throws DaoException;
}
