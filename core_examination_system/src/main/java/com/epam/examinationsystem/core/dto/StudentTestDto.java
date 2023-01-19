package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Test;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StudentTestDto extends AbstractDto {

    private final String name;
    private final String description;
    private final String complexity;
    private final String duration;
    private final String totalAttemptNumber;
    private final String creationDate;
    private final String expirationDate;
    private final String maxAttemptNumber;
    private final List<QuestionForStudentTestDto> questions;

    public StudentTestDto(StudentTestDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
        this.complexity = builder.complexity;
        this.duration = builder.duration;
        this.totalAttemptNumber = builder.totalAttemptNumber;
        this.creationDate = builder.creationDate;
        this.expirationDate = builder.expirationDate;
        this.maxAttemptNumber = builder.maxAttemptNumber;
        this.questions = builder.questions;
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

    public List<QuestionForStudentTestDto> getQuestions() {
        return questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentTestDto that)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(name)
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
                .append("creationDate", creationDate)
                .append("expirationDate", expirationDate)
                .append("maxAttemptNumber", maxAttemptNumber)
                .append("questions", questions)
                .toString();
    }

    public record QuestionForStudentTestDto(
            String uuid,
            String type,
            String content,
            String description,
            List<AnswerOnQuestionForStudentTestDto> answers) {

        public record AnswerOnQuestionForStudentTestDto(String uuid, String content, String isCorrect) {

            public String getUuid() {
                return uuid;
            }

            public String getContent() {
                return content;
            }

            public String getIsCorrect() {
                return isCorrect;
            }
        }

        public String getUuid() {
            return uuid;
        }

        public String getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        public String getDescription() {
            return description;
        }

        public List<AnswerOnQuestionForStudentTestDto> getAnswers() {
            return answers;
        }
    }

    public static StudentTestDtoBuilder builder() {
        return new StudentTestDtoBuilder();
    }

    public static class StudentTestDtoBuilder {

        private String uuid;
        private String name;
        private String description;
        private String complexity;
        private String duration;
        private String totalAttemptNumber;
        private String creationDate;
        private String expirationDate;
        private String maxAttemptNumber;
        private List<QuestionForStudentTestDto> questions;

        public StudentTestDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public StudentTestDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentTestDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public StudentTestDtoBuilder setComplexity(String complexity) {
            this.complexity = complexity;
            return this;
        }

        public StudentTestDtoBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        public StudentTestDtoBuilder setTotalAttemptNumber(String totalAttemptNumber) {
            this.totalAttemptNumber = totalAttemptNumber;
            return this;
        }

        public StudentTestDtoBuilder setCreationDate(String creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public StudentTestDtoBuilder setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public StudentTestDtoBuilder setMaxAttemptNumber(String maxAttemptNumber) {
            this.maxAttemptNumber = maxAttemptNumber;
            return this;
        }

        public StudentTestDtoBuilder setQuestions(List<QuestionForStudentTestDto> questions) {
            this.questions = questions;
            return this;
        }

        public StudentTestDto build() {
            return new StudentTestDto(this);
        }

        public StudentTestDto fromTest(Test test, List<QuestionForStudentTestDto> questions) {
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
                    .setQuestions(questions)
                    .build();
        }
    }
}
