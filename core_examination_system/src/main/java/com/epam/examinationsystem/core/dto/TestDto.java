package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TestDto for the test entity.
 */
public class TestDto extends AbstractDto {

    private final String name;
    private final String description;
    private final String complexity;
    private final String duration;
    private final String totalAttemptNumber;
    private final String currentAttemptNumber;
    private final String isSelected;
    private final String creationDate;
    private final String expirationDate;
    private final String maxAttemptNumber;
    private final SubjectForTest subject;
    private final String isAvailable;

    public TestDto(TestDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.complexity = builder.complexity;
        this.duration = builder.duration;
        this.totalAttemptNumber = builder.totalAttemptNumber;
        this.currentAttemptNumber = builder.currentAttemptNumber;
        this.isSelected = builder.isSelected;
        this.subject = builder.subject;
        this.creationDate = builder.creationDate;
        this.expirationDate = builder.expirationDate;
        this.maxAttemptNumber = builder.maxAttemptNumber;
        this.isAvailable = builder.isAvailable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getComplexity() {
        return complexity;
    }

    public String getDuration() {
        return duration;
    }

    public String getTotalAttemptNumber() {
        return totalAttemptNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getMaxAttemptNumber() {
        return maxAttemptNumber;
    }

    public SubjectForTest getSubject() {
        return subject;
    }

    public String getCurrentAttemptNumber() {
        return currentAttemptNumber;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public String getIsAvailable() {
        return isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestDto testDto)) return false;
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
                .append("name", name)
                .append("description", description)
                .append("complexity", complexity)
                .append("duration", duration)
                .append("totalAttemptNumber", totalAttemptNumber)
                .append("currentAttemptNumber", currentAttemptNumber)
                .append("creationDate", creationDate)
                .append("expirationDate", expirationDate)
                .append("maxAttemptNumber", maxAttemptNumber)
                .append("subject", subject)
                .toString();
    }

    public record SubjectForTest(String uuid, String name) {

        public String getUuid() {
            return uuid;
        }

        public String getName() {
            return name;
        }
    }

    public static TestDtoBuilder builder() {
        return new TestDtoBuilder();
    }

    public static class TestDtoBuilder {

        private String uuid;
        private String name;
        private String description;
        private String complexity;
        private String duration;
        private String totalAttemptNumber;
        private String currentAttemptNumber;
        private String isSelected;
        private String creationDate;
        private String expirationDate;
        private String maxAttemptNumber;
        private SubjectForTest subject;
        private String isAvailable;

        public TestDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public TestDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TestDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public TestDtoBuilder setComplexity(String complexity) {
            this.complexity = complexity;
            return this;
        }

        public TestDtoBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public TestDtoBuilder setTotalAttemptNumber(String totalAttemptNumber) {
            this.totalAttemptNumber = totalAttemptNumber;
            return this;
        }

        public TestDtoBuilder setCurrentAttemptNumber(String currentAttemptNumber) {
            this.currentAttemptNumber = currentAttemptNumber;
            return this;
        }

        public TestDtoBuilder setIsSelected(String isSelected) {
            this.isSelected = isSelected;
            return this;
        }

        public TestDtoBuilder setSubject(SubjectForTest subject) {
            this.subject = subject;
            return this;
        }

        public TestDtoBuilder setCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public TestDtoBuilder setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public TestDtoBuilder setMaxAttemptNumber(String maxAttemptNumber) {
            this.maxAttemptNumber = maxAttemptNumber;
            return this;
        }

        public TestDtoBuilder setIsAvailable(String isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public TestDto build() {
            return new TestDto(this);
        }

        public TestDto fromTest(Test test) {
            Subject testSubject = test.getSubject();
            SubjectForTest subjectForTest = null;
            if (testSubject != null) {
                subjectForTest = new SubjectForTest(testSubject.getUuid().toString(), testSubject.getName());
            }
            return this
                    .setUuid(test.getUuid().toString())
                    .setName(test.getName())
                    .setDescription(test.getDescription())
                    .setComplexity(test.getComplexity().toString())
                    .setDuration(String.valueOf(test.getDuration()))
                    .setTotalAttemptNumber(String.valueOf(test.getTotalAttemptNumber()))
                    .setCreationDate(test.getCreated().toString())
                    .setExpirationDate(test.getExpirationDate() != null ? test.getExpirationDate().toString() : null)
                    .setMaxAttemptNumber(String.valueOf(test.getMaxAttemptNumber()))
                    .setSubject(subjectForTest)
                    .build();
        }
    }
}
