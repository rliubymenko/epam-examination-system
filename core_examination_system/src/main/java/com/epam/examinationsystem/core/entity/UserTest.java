package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserTest extends AbstractEntity {

    private final User user;
    private final Test test;
    private final Boolean isSelected;
    private final Boolean isCompleted;
    private final Float markValue;
    private final Integer attemptNumber;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public UserTest(UserTestBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        super.created = builder.created;
        this.user = builder.user;
        this.test = builder.test;
        this.isSelected = builder.isSelected;
        this.isCompleted = builder.isCompleted;
        this.markValue = builder.markValue;
        this.attemptNumber = builder.attemptNumber;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
    }

    public User getUser() {
        return user;
    }

    public Test getTest() {
        return test;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public Float getMarkValue() {
        return markValue;
    }

    public Integer getAttemptNumber() {
        return attemptNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTest userTest)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("user", user.getUsername())
                .append("test", test.getName())
                .append("isSelected", isSelected)
                .append("isCompleted", isCompleted)
                .append("markValue", markValue)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("attemptNumber", attemptNumber)
                .toString();
    }

    public static UserTestBuilder builder() {
        return new UserTestBuilder();
    }

    public static class UserTestBuilder {

        private Long id;
        private UUID uuid;
        private User user;
        private Test test;
        private Boolean isSelected;
        private Boolean isCompleted;
        private Float markValue;
        private Integer attemptNumber;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private LocalDateTime created;

        public UserTestBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserTestBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserTestBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public UserTestBuilder setTest(Test test) {
            this.test = test;
            return this;
        }

        public UserTestBuilder setIsSelected(Boolean isSelected) {
            this.isSelected = isSelected;
            return this;
        }

        public UserTestBuilder setIsCompleted(Boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public UserTestBuilder setMarkValue(Float markValue) {
            this.markValue = markValue;
            return this;
        }

        public UserTestBuilder setAttemptNumber(Integer attemptNumber) {
            this.attemptNumber = attemptNumber;
            return this;
        }

        public UserTestBuilder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public UserTestBuilder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public UserTestBuilder setCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public UserTest build() {
            return new UserTest(this);
        }
    }
}
