package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Subject;
import com.epam.examinationsystem.core.entity.Test;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TestDto extends AbstractDto {

    private final String name;
    private final String description;
    private final String complexity;
    private final String duration;
    private final String totalAttemptNumber;
    private final String creationDate;
    private final String expirationDate;
    private final String maxAttemptNumber;
    private final SubjectForTest subject;

    public TestDto(TestDtoBuilder builder) {
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
        private String creationDate;
        private String expirationDate;
        private String maxAttemptNumber;
        private SubjectForTest subject;


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
