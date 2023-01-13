package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Answer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AnswerDto extends AbstractDto {

    private final String content;
    private final String isCorrect;
    private final QuestionForAnswer question;

    private AnswerDto(AnswerDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.content = builder.content;
        this.isCorrect = builder.isCorrect;
        this.question = builder.question;
    }

    public String getContent() {
        return content;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerDto answerDto)) return false;
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
                .append("content", content)
                .append("isCorrect", isCorrect)
                .append("question", question)
                .toString();
    }

    public record QuestionForAnswer(String uuid, String content) {
        public String getUuid() {
            return uuid;
        }

        public String getContent() {
            return content;
        }
    }

    public static AnswerDtoBuilder builder() {
        return new AnswerDtoBuilder();
    }

    public static class AnswerDtoBuilder {

        private String uuid;
        private String content;
        private String isCorrect;
        private QuestionForAnswer question;

        public AnswerDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public AnswerDtoBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public AnswerDtoBuilder setIsCorrect(String isCorrect) {
            this.isCorrect = isCorrect;
            return this;
        }

        public AnswerDtoBuilder setQuestion(QuestionForAnswer question) {
            this.question = question;
            return this;
        }

        public AnswerDto build() {
            return new AnswerDto(this);
        }

        public AnswerDto fromAnswer(Answer answer) {
            QuestionForAnswer questionForAnswer = new QuestionForAnswer(
                    answer.getQuestion().getUuid().toString(),
                    answer.getContent()
            );
            return this
                    .setUuid(answer.getUuid().toString())
                    .setContent(answer.getContent())
                    .setIsCorrect(answer.getIsCorrect().toString())
                    .setQuestion(questionForAnswer)
                    .build();
        }
    }
}
