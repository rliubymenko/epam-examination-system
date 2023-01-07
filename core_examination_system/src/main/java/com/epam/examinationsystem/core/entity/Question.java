package com.epam.examinationsystem.core.entity;

import com.epam.examinationsystem.core.enumeration.QuestionType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class Question extends AbstractEntity {

    private final QuestionType type;
    private final String content;
    private final String description;
    private final Test test;

    private Question(QuestionBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        this.type = builder.type;
        this.content = builder.content;
        this.description = builder.description;
        this.test = builder.test;
    }

    public QuestionType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public Test getTest() {
        return test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question question)) return false;
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
                .appendSuper(super.toString())
                .append("type", type.toString())
                .append("content", content)
                .append("description", description)
                .append("test", test.getName())
                .toString();
    }

    public static QuestionBuilder builder() {
        return new QuestionBuilder();
    }

    public static class QuestionBuilder {

        private Long id;
        private UUID uuid;
        private QuestionType type;
        private String content;
        private String description;
        private Test test;

        public QuestionBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public QuestionBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public QuestionBuilder setType(QuestionType type) {
            this.type = type;
            return this;
        }

        public QuestionBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public QuestionBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public QuestionBuilder setTest(Test test) {
            this.test = test;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
