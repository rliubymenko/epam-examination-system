package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

public class Answer extends AbstractEntity {

    private final String content;
    private final Boolean isCorrect;
    private final Question question;

    private Answer(AnswerBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        super.created = builder.created;
        this.content = builder.content;
        this.isCorrect = builder.isCorrect;
        this.question = builder.question;
    }

    public String getContent() {
        return content;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer answer)) return false;
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
                .append("content", content)
                .append("isCorrect", isCorrect)
                .append("question", question.getContent())
                .toString();
    }

    public static AnswerBuilder builder() {
        return new AnswerBuilder();
    }

    public static class AnswerBuilder {

        private Long id;
        private UUID uuid;
        private String content;
        private Boolean isCorrect;
        private Question question;
        private LocalDateTime created;

        public AnswerBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public AnswerBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public AnswerBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public AnswerBuilder setIsCorrect(Boolean isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public AnswerBuilder setQuestion(Question question) {
            this.question = question;
            return this;
        }

        public AnswerBuilder setCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Answer build() {
            return new Answer(this);
        }
    }
}
