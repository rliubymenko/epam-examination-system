package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.Subject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SubjectDto extends AbstractDto {

    private final String name;
    private final String description;

    private SubjectDto(SubjectDtoBuilder builder) {
        super.uuid = builder.uuid;
        this.name = builder.name;
        this.description = builder.description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
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

    public static SubjectDtoBuilder builder() {
        return new SubjectDtoBuilder();
    }

    public static class SubjectDtoBuilder {

        private String uuid;
        private String name;
        private String description;

        public SubjectDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public SubjectDtoBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SubjectDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SubjectDto build() {
            return new SubjectDto(this);
        }

        public SubjectDto fromSubject(Subject subject) {
            return this
                    .setUuid(subject.getUuid().toString())
                    .setName(subject.getName())
                    .setDescription(subject.getDescription())
                    .build();
        }
    }
}
