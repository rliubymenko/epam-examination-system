package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Test;
import com.epam.examinationsystem.core.entity.User;
import com.epam.examinationsystem.core.entity.UserTest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserTestDto extends AbstractDto {

    private final String isSelected;
    private final String isCompleted;
    private final String markValue;
    private final String attemptNumber;
    private final String startTime;
    private final String endTime;
    private final UserAdjacent user;
    private final TestAdjacent test;

    public UserTestDto(UserTestDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.user = builder.user;
        this.test = builder.test;
        this.isSelected = builder.isSelected;
        this.isCompleted = builder.isCompleted;
        this.markValue = builder.markValue;
        this.attemptNumber = builder.attemptNumber;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public UserAdjacent getUser() {
        return user;
    }

    public TestAdjacent getTest() {
        return test;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public String getIsCompleted() {
        return isCompleted;
    }

    public String getMarkValue() {
        return markValue;
    }

    public String getAttemptNumber() {
        return attemptNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTestDto that)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("isSelected", isSelected)
                .append("isCompleted", isCompleted)
                .append("markValue", markValue)
                .append("attemptNumber", attemptNumber)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("user", user)
                .append("test", test)
                .toString();
    }

    public record UserAdjacent(String uuid, String username) {

        public String getUuid() {
            return uuid;
        }

        public String getUsername() {
            return username;
        }
    }

    public record TestAdjacent(String uuid,
                               String name,
                               String complexity,
                               String maxAttemptNumber,
                               String expirationDate,
                               String duration) {

        public TestAdjacent(String uuid, String name) {
            this(uuid, name, null, null, null, null);
        }

        public String getUuid() {
            return uuid;
        }

        public String getName() {
            return name;
        }

        public String getComplexity() {
            return complexity;
        }

        public String getMaxAttemptNumber() {
            return maxAttemptNumber;
        }

        public String getExpirationDate() {
            return expirationDate;
        }

        public String getDuration() {
            return duration;
        }
    }

    public static UserTestDtoBuilder builder() {
        return new UserTestDtoBuilder();
    }

    public static class UserTestDtoBuilder {

        private String uuid;
        private UserAdjacent user;
        private TestAdjacent test;
        private String isSelected;
        private String isCompleted;
        private String markValue;
        private String attemptNumber;
        private String startTime;
        private String endTime;

        public UserTestDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserTestDtoBuilder setUser(UserAdjacent user) {
            this.user = user;
            return this;
        }

        public UserTestDtoBuilder setTest(TestAdjacent test) {
            this.test = test;
            return this;
        }

        public UserTestDtoBuilder setIsSelected(String isSelected) {
            this.isSelected = isSelected;
            return this;
        }

        public UserTestDtoBuilder setIsCompleted(String isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public UserTestDtoBuilder setMarkValue(String markValue) {
            this.markValue = markValue;
            return this;
        }

        public UserTestDtoBuilder setAttemptNumber(String attemptNumber) {
            this.attemptNumber = attemptNumber;
            return this;
        }

        public UserTestDtoBuilder setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public UserTestDtoBuilder setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public UserTestDto build() {
            return new UserTestDto(this);
        }

        public UserTestDto fromUserTest(UserTest userTest) {
            User currentUser = userTest.getUser();
            Test currentTest = userTest.getTest();
            UserAdjacent userAdjacent = new UserAdjacent(currentUser.getUuid().toString(), currentUser.getUsername());
            TestAdjacent testAdjacent = new TestAdjacent(
                    currentTest.getUuid().toString(),
                    currentTest.getName(),
                    currentTest.getComplexity().toString(),
                    String.valueOf(currentTest.getMaxAttemptNumber()),
                    currentTest.getExpirationDate() != null ? currentTest.getExpirationDate().toString() : null,
                    String.valueOf(currentTest.getDuration())
            );
            return this
                    .setUuid(userTest.getUuid().toString())
                    .setIsSelected(userTest.getIsSelected().toString())
                    .setIsCompleted(userTest.getIsCompleted().toString())
                    .setMarkValue(userTest.getMarkValue() != null ? userTest.getMarkValue().toString() : null)
                    .setAttemptNumber(userTest.getAttemptNumber().toString())
                    .setStartTime(userTest.getStartTime() != null ? userTest.getStartTime().toString() : null)
                    .setEndTime(userTest.getEndTime() != null ? userTest.getEndTime().toString() : null)
                    .setUser(userAdjacent)
                    .setTest(testAdjacent)
                    .build();
        }
    }
}

