package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Subject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * StudentSubjectDto for the subject entity to be represented for the student.
 */
public class StudentSubjectDto extends AbstractDto {

    private final String name;
    private final String description;
    private final List<TestForStudentSubjectDto> tests;

    private StudentSubjectDto(StudentSubjectDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.tests = builder.tests;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<TestForStudentSubjectDto> getTests() {
        return tests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSubjectDto that = (StudentSubjectDto) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, that.name)
                .append(description, that.description)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(name)
                .append(description)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("name", name)
                .append("description", description)
                .toString();
    }

    public record TestForStudentSubjectDto(
            String uuid,
            String name,
            String complexity,
            String maxAttemptNumber,
            String currentAttemptNumber,
            String isSelected,
            String expirationDate,
            String duration,
            String isAvailable) {

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

        public String getCurrentAttemptNumber() {
            return currentAttemptNumber;
        }

        public String getIsSelected() {
            return isSelected;
        }

        public String getIsAvailable() {
            return isAvailable;
        }
    }

    public static StudentSubjectDtoBuilder builder() {
        return new StudentSubjectDtoBuilder();
    }

    public static class StudentSubjectDtoBuilder {

        private String uuid;
        private String name;
        private String description;
        private List<TestForStudentSubjectDto> tests;

        public StudentSubjectDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public StudentSubjectDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentSubjectDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public StudentSubjectDtoBuilder setTests(List<TestForStudentSubjectDto> tests) {
            this.tests = tests;
            return this;
        }

        public StudentSubjectDto build() {
            return new StudentSubjectDto(this);
        }

        public StudentSubjectDto fromSubject(Subject subject, List<TestForStudentSubjectDto> tests) {
            return this
                    .setUuid(subject.getUuid().toString())
                    .setName(subject.getName())
                    .setDescription(subject.getDescription())
                    .setTests(tests)
                    .build();
        }
    }
}
