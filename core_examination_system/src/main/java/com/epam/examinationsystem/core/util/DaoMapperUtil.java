package com.epam.examinationsystem.core.util;

import com.epam.examinationsystem.core.dao.*;
import com.epam.examinationsystem.core.entity.*;
import com.epam.examinationsystem.core.enumeration.QuestionType;
import com.epam.examinationsystem.core.enumeration.TestComplexity;
import com.epam.examinationsystem.core.enumeration.UserType;
import com.epam.examinationsystem.core.exception.DaoException;
import org.apache.commons.lang3.EnumUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DaoMapperUtil {

    private DaoMapperUtil() {
    }

    public static Role extractRole(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setUuid(UUID.fromString(resultSet.getString("uuid")));
        role.setName(UserType.valueOf(resultSet.getString("name")));
        return role;
    }

    public static User extractUser(ResultSet resultSet, RoleDao roleDao) throws SQLException, DaoException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUuid(UUID.fromString(resultSet.getString("uuid")));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setIsActivated(resultSet.getBoolean("is_activated"));
        Role role = roleDao.getById(resultSet.getLong("role_id"));
        user.setRole(role);
        return user;
    }

    public static Subject extractSubject(ResultSet resultSet, UserDao userDao) throws SQLException, DaoException {
        Subject subject = new Subject();
        subject.setId(resultSet.getLong("id"));
        subject.setUuid(UUID.fromString(resultSet.getString("uuid")));
        subject.setName(resultSet.getString("name"));
        subject.setDescription(resultSet.getString("description"));
        User user = userDao.getById(resultSet.getLong("epam_user_id"));
        subject.setUser(user);
        return subject;
    }

    public static Test extractTest(ResultSet resultSet, SubjectDao subjectDao) throws SQLException, DaoException {
        Test test = getTest(resultSet);
        Subject subject = subjectDao.getById(resultSet.getLong("subject_id"));
        test.setSubject(subject);
        return test;
    }

    public static Test extractTestWithoutSubject(ResultSet resultSet) throws SQLException {
        return getTest(resultSet);
    }

    private static Test getTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong("id"));
        test.setUuid(UUID.fromString(resultSet.getString("uuid")));
        test.setName(resultSet.getString("name"));
        test.setDescription(resultSet.getString("description"));
        test.setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, resultSet.getString("complexity")));
        test.setDuration(resultSet.getInt("duration"));
        test.setTotalAttemptNumber(resultSet.getInt("total_attempt_number"));
        test.setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime());
        test.setExpirationDate(resultSet.getTimestamp("expiration_date").toLocalDateTime());
        test.setMaxAttemptNumber(resultSet.getInt("max_attempt_number"));
        return test;
    }

    public static Question extractQuestion(ResultSet resultSet, TestDao testDao) throws SQLException, DaoException {
        Question question = new Question();
        question.setId(resultSet.getLong("id"));
        question.setUuid(UUID.fromString(resultSet.getString("uuid")));
        question.setType(EnumUtils.getEnum(QuestionType.class, resultSet.getString("type")));
        question.setContent(resultSet.getString("content"));
        question.setDescription(resultSet.getString("description"));
        Test test = testDao.getById(resultSet.getLong("test_id"));
        question.setTest(test);
        return question;
    }

    public static Answer extractAnswer(ResultSet resultSet, QuestionDao questionDao) throws SQLException, DaoException {
        Answer answer = new Answer();
        answer.setId(resultSet.getLong("id"));
        answer.setUuid(UUID.fromString(resultSet.getString("uuid")));
        answer.setContent(resultSet.getString("content"));
        answer.setIsCorrect(resultSet.getBoolean("is_correct"));
        Question question = questionDao.getById(resultSet.getLong("question_id"));
        answer.setQuestion(question);
        return answer;
    }

    public static UserTest extractUserTest(ResultSet resultSet, UserDao userDao, TestDao testDao) throws SQLException, DaoException {
        UserTest userTest = new UserTest();
        userTest.setId(resultSet.getLong("id"));
        userTest.setUuid(UUID.fromString(resultSet.getString("uuid")));
        userTest.setIsSelected(resultSet.getBoolean("is_selected"));
        userTest.setIsCompleted(resultSet.getBoolean("is_completed"));
        userTest.setMarkValue(resultSet.getInt("mark_value"));
        userTest.setAttemptNumber(resultSet.getInt("attempt_number"));
        userTest.setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime());
        userTest.setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime());
        User user = userDao.getById(resultSet.getLong("epam_user_id"));
        Test test = testDao.getById(resultSet.getLong("test_id"));
        userTest.setUser(user);
        userTest.setTest(test);
        return userTest;
    }
}
