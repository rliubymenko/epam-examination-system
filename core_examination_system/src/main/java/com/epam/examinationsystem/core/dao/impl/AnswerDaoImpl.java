package com.epam.examinationsystem.core.dao.impl;

import com.epam.di.annotation.PleaseInject;
import com.epam.di.annotation.PleaseService;
import com.epam.examinationsystem.core.dao.AnswerDao;
import com.epam.examinationsystem.core.dao.QuestionDao;
import com.epam.examinationsystem.core.dao.common.AbstractDao;
import com.epam.examinationsystem.core.entity.Answer;
import com.epam.examinationsystem.core.enumeration.DaoConstant;
import com.epam.examinationsystem.core.exception.DaoException;
import com.epam.examinationsystem.core.util.db.DaoMapperUtil;
import com.epam.examinationsystem.core.util.db.QueryBuilderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@PleaseService
public class AnswerDaoImpl extends AbstractDao<Answer> implements AnswerDao {

    private static final String ENTITY_NAME = "answer";
    private static final Logger LOG = LoggerFactory.getLogger(AnswerDaoImpl.class);

    @PleaseInject
    private QuestionDao questionDao;

    public AnswerDaoImpl() {
        super(LOG, ENTITY_NAME, DaoConstant.ANSWER_TABLE_NAME.getValue());
    }

    @Override
    public Answer extractEntity(ResultSet resultSet) throws SQLException, DaoException {
        Answer answer = null;
        while (resultSet.next()) {
            answer = DaoMapperUtil.extractAnswer(resultSet, questionDao);
        }
        return answer;
    }

    @Override
    public List<Answer> extractEntities(ResultSet resultSet) throws SQLException, DaoException {
        List<Answer> answers = new ArrayList<>();
        while (resultSet.next()) {
            Answer answer = DaoMapperUtil.extractAnswer(resultSet, questionDao);
            answers.add(answer);
        }
        return answers;
    }

    @Override
    public String getInsertQuery() {
        return QueryBuilderUtil.generateInsertQuery(DaoConstant.ANSWER_TABLE_NAME.getValue(), 3);
    }

    @Override
    public String getUpdateQuery(Answer entity) {
        List<String> columnNames = List.of("content", "is_correct");
        return QueryBuilderUtil.generateUpdateQueryByUuid(DaoConstant.ANSWER_TABLE_NAME.getValue(), entity.getUuid(), columnNames);
    }

    @Override
    public void populateInsertQuery(PreparedStatement preparedStatement, Answer entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getContent(),
                entity.getIsCorrect(),
                entity.getQuestion().getId()
        );
    }

    @Override
    public void populateUpdateQuery(PreparedStatement preparedStatement, Answer entity) throws SQLException {
        QueryBuilderUtil.populatePreparedStatement(
                preparedStatement,
                entity.getContent(),
                entity.getIsCorrect()
        );
    }
}
