package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.TestDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.LoggerUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@PleaseService
public class QuestionDaoImpl extends AbstractDao<Question> implements QuestionDao {

    private static final String ENTITY_NAME = "question";
    private static final Logger LOG = LoggerFactory.getLogger(QuestionDaoImpl.class);

    @PleaseInject
    private TestDao testDao;

    public QuestionDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.QUESTION_TABLE_NAME.getValue());
    }

    @Override
    public Question extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Question question = null;
        while (resultSet.next()) {
            question = DaoMapperUtil.extractQuestion(resultSet, testDao);
        }
        return question;
    }

    @Override
    public List<Question> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Question> questions = new ArrayList<>();
        while (resultSet.next()) {
            Question question = DaoMapperUtil.extractQuestion(resultSet, testDao);
            questions.add(question);
        }
        return questions;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.QUESTION_TABLE_NAME.getValue(), 4);
    }

    @Override
    public String getUpdateQuery(Question entity) {
        List<String> columnNames = List.of("type", "content", "description", "test_id");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.QUESTION_TABLE_NAME.getValue(), entity.getUuid(), columnNames);

    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Question entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getType(),
                entity.getContent(),
                entity.getDescription(),
                entity.getTest().getId()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Question entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getType(),
                entity.getContent(),
                entity.getDescription()
        );
    }
}
