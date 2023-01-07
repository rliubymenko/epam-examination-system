package com.epam.examinationsystem.core.util.db;

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
        return Role.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(EnumUtils.getEnumIgnoreCase(UserType.class, resultSet.getString("name")))
                .build();
    }

    public static User extractUser(ResultSet resultSet, RoleDao roleDao) throws SQLException, DaoException {
        return User.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setEmail(resultSet.getString("email"))
                .setFirstName(resultSet.getString("first_name"))
                .setLastName(resultSet.getString("last_name"))
                .setIsActivated(resultSet.getBoolean("is_activated"))
                .setRole(roleDao.getById(resultSet.getLong("role_id")))
                .build();
    }

    public static Subject extractSubject(ResultSet resultSet, UserDao userDao) throws SQLException, DaoException {
        return Subject.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setUser(userDao.getById(resultSet.getLong("epam_user_id")))
                .build();
    }

    public static Test extractTest(ResultSet resultSet, SubjectDao subjectDao) throws SQLException, DaoException {
        Test.TestBuilder builder = getTest(resultSet);
        builder.setSubject(subjectDao.getById(resultSet.getLong("subject_id")));
        return builder.build();
    }

    public static Test extractTestWithoutSubject(ResultSet resultSet) throws SQLException {
        Test.TestBuilder builder = getTest(resultSet);
        return builder.build();
    }

    private static Test.TestBuilder getTest(ResultSet resultSet) throws SQLException {
        return Test.builder().setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, resultSet.getString("complexity")))
                .setDuration(resultSet.getInt("duration"))
                .setTotalAttemptNumber(resultSet.getInt("total_attempt_number"))
                .setCreationDate(resultSet.getTimestamp("creation_date").toLocalDateTime())
                .setExpirationDate(resultSet.getTimestamp("expiration_date").toLocalDateTime())
                .setMaxAttemptNumber(resultSet.getInt("max_attempt_number"));
    }

    public static Question extractQuestion(ResultSet resultSet, TestDao testDao) throws SQLException, DaoException {
        return Question.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setType(EnumUtils.getEnumIgnoreCase(QuestionType.class, resultSet.getString("type")))
                .setContent(resultSet.getString("content"))
                .setDescription(resultSet.getString("description"))
                .setTest(testDao.getById(resultSet.getLong("test_id")))
                .build();
    }

    public static Answer extractAnswer(ResultSet resultSet, QuestionDao questionDao) throws SQLException, DaoException {
        return Answer.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setContent(resultSet.getString("content"))
                .setIsCorrect(resultSet.getBoolean("is_correct"))
                .setQuestion(questionDao.getById(resultSet.getLong("question_id")))
                .build();
    }

    public static UserTest extractUserTest(ResultSet resultSet, UserDao userDao, TestDao testDao) throws SQLException, DaoException {
        return UserTest.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setIsSelected(resultSet.getBoolean("is_selected"))
                .setIsCompleted(resultSet.getBoolean("is_completed"))
                .setMarkValue(resultSet.getInt("mark_value"))
                .setAttemptNumber(resultSet.getInt("attempt_number"))
                .setStartTime(resultSet.getTimestamp("start_time").toLocalDateTime())
                .setEndTime(resultSet.getTimestamp("end_time").toLocalDateTime())
                .setUser(userDao.getById(resultSet.getLong("epam_user_id")))
                .setTest(testDao.getById(resultSet.getLong("test_id")))
                .build();
    }
}