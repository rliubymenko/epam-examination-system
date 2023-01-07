package com.epam.examinationsystem.core.entity;

import com.epam.examinationsystem.core.enumeration.TestComplexity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class Test extends AbstractEntity {

    private final String name;
    private final String description;
    private final TestComplexity complexity;
    private final Integer duration;
    private final Integer totalAttemptNumber;
    private final Subject subject;
    private final LocalDateTime creationDate;
    private final LocalDateTime expirationDate;
    private final Integer maxAttemptNumber;

    public Test(TestBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.complexity = builder.complexity;
        this.duration = builder.duration;
        this.totalAttemptNumber = builder.totalAttemptNumber;
        this.subject = builder.subject;
        this.creationDate = builder.creationDate;
        this.expirationDate = builder.expirationDate;
        this.maxAttemptNumber = builder.maxAttemptNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public TestComplexity getComplexity() {
        return complexity;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getTotalAttemptNumber() {
        return totalAttemptNumber;
    }

    public Subject getSubject() {
        return subject;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public Integer getMaxAttemptNumber() {
        return maxAttemptNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test test)) return false;
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
                .append("name", name)
                .append("description", description)
                .append("complexity", complexity)
                .append("duration", duration)
                .append("totalAttemptNumber", totalAttemptNumber)
                .append("maxAttemptNumber", maxAttemptNumber)
                .append("subject", subject.getName())
                .append("creationDate", creationDate)
                .append("expirationDate", expirationDate)
                .toString();
    }

    public static TestBuilder builder() {
        return new TestBuilder();
    }

    public static class TestBuilder {

        private Long id;
        private UUID uuid;
        private String name;
        private String description;
        private TestComplexity complexity;
        private Integer duration;
        private Integer totalAttemptNumber;
        private Subject subject;
        private LocalDateTime creationDate;
        private LocalDateTime expirationDate;
        private Integer maxAttemptNumber;

        public TestBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public TestBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public TestBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TestBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TestBuilder setComplexity(TestComplexity complexity) {
            this.complexity = complexity;
            return this;
        }

        public TestBuilder setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public TestBuilder setTotalAttemptNumber(Integer totalAttemptNumber) {
            this.totalAttemptNumber = totalAttemptNumber;
            return this;
        }

        public TestBuilder setSubject(Subject subject) {
            this.subject = subject;
            return this;
        }

        public TestBuilder setCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public TestBuilder setExpirationDate(LocalDateTime expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public TestBuilder setMaxAttemptNumber(Integer maxAttemptNumber) {
            this.maxAttemptNumber = maxAttemptNumber;
            return this;
        }

        public Test build() {
            return new Test(this);
        }
    }
}
