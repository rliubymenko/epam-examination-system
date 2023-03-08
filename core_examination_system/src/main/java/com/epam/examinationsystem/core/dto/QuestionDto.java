package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Question;
import com.epam.examinationsystem.core.entity.Test;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * QuestionDto for the question entity.
 */
public class QuestionDto extends AbstractDto {

    private final String type;
    private final String content;
    private final String description;
    private final TestForQuestion test;

    private QuestionDto(QuestionDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.type = builder.type;
        this.content = builder.content;
        this.description = builder.description;
        this.test = builder.test;
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

    public TestForQuestion getTest() {
        return test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionDto that)) return false;
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

    public record TestForQuestion(String uuid, String name) {

        public String getUuid() {
            return uuid;
        }

        public String getName() {
            return name;
        }
    }

    public static QuestionDtoBuilder builder() {
        return new QuestionDtoBuilder();
    }

    public static class QuestionDtoBuilder {

        private String uuid;
        private String type;
        private String content;
        private String description;
        private TestForQuestion test;

        public QuestionDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public QuestionDtoBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public QuestionDtoBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public QuestionDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public QuestionDtoBuilder setTest(TestForQuestion test) {
            this.test = test;
            return this;
        }

        public QuestionDto fromQuestion(Question question) {
            Test questionTest = question.getTest();
            TestForQuestion testForQuestion = new TestForQuestion(questionTest.getUuid().toString(), questionTest.getName());
            return this
                    .setUuid(question.getUuid().toString())
                    .setType(question.getType().toString())
                    .setContent(question.getContent())
                    .setDescription(question.getDescription())
                    .setTest(testForQuestion)
                    .build();
        }

        public QuestionDto build() {
            return new QuestionDto(this);
        }
    }
}
