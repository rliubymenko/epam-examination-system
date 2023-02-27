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
import java.sql.Timestamp;
import java.util.UUID;

public class DaoMapperUtil {

    private DaoMapperUtil() {
    }

    public static Role extractRole(ResultSet resultSet) throws SQLException {
        return Role.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(EnumUtils.getEnumIgnoreCase(UserType.class, resultSet.getString("name")))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
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
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
                .build();
    }

    public static Subject extractSubject(ResultSet resultSet) throws SQLException {
        return Subject.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
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
        Timestamp maybeExpirationDate = resultSet.getTimestamp("expiration_date");
        return Test.builder().setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setName(resultSet.getString("name"))
                .setDescription(resultSet.getString("description"))
                .setComplexity(EnumUtils.getEnumIgnoreCase(TestComplexity.class, resultSet.getString("complexity")))
                .setDuration(resultSet.getInt("duration"))
                .setTotalAttemptNumber(resultSet.getInt("total_attempt_number"))
                .setExpirationDate(maybeExpirationDate != null ? maybeExpirationDate.toLocalDateTime() : null)
                .setMaxAttemptNumber(resultSet.getInt("max_attempt_number"))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime());
    }

    public static Question extractQuestion(ResultSet resultSet, TestDao testDao) throws SQLException, DaoException {
        return Question.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setType(EnumUtils.getEnumIgnoreCase(QuestionType.class, resultSet.getString("type")))
                .setContent(resultSet.getString("content"))
                .setDescription(resultSet.getString("description"))
                .setTest(testDao.getById(resultSet.getLong("test_id")))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
                .build();
    }

    public static Answer extractAnswer(ResultSet resultSet, QuestionDao questionDao) throws SQLException, DaoException {
        return Answer.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setContent(resultSet.getString("content"))
                .setIsCorrect(resultSet.getBoolean("is_correct"))
                .setQuestion(questionDao.getById(resultSet.getLong("question_id")))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
                .build();
    }

    public static UserTest extractUserTest(ResultSet resultSet, UserDao userDao, TestDao testDao) throws SQLException, DaoException {
        Timestamp maybeStartTime = resultSet.getTimestamp("start_time");
        Timestamp maybeEndTime = resultSet.getTimestamp("end_time");
        String maybeMarkValue = resultSet.getString("mark_value");
        return UserTest.builder()
                .setId(resultSet.getLong("id"))
                .setUuid(UUID.fromString(resultSet.getString("uuid")))
                .setIsSelected(resultSet.getBoolean("is_selected"))
                .setIsCompleted(resultSet.getBoolean("is_completed"))
                .setMarkValue(maybeMarkValue != null ? Float.parseFloat(maybeMarkValue) : null)
                .setAttemptNumber(resultSet.getInt("attempt_number"))
                .setStartTime(maybeStartTime != null ? maybeStartTime.toLocalDateTime() : null)
                .setEndTime(maybeEndTime != null ? maybeEndTime.toLocalDateTime() : null)
                .setUser(userDao.getById(resultSet.getLong("epam_user_id")))
                .setTest(testDao.getById(resultSet.getLong("test_id")))
                .setCreated(resultSet.getTimestamp("created").toLocalDateTime())
                .build();
    }
}
