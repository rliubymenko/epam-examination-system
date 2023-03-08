package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The subject entity
 */
public class Subject extends AbstractEntity {

    private final String name;
    private final String description;

    public Subject(SubjectBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        super.created = builder.created;
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
        if (!(o instanceof Subject subject)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, subject.name)
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
                .appendSuper(super.toString())
                .append("name", name)
                .append("description", description)
                .toString();
    }

    public static SubjectBuilder builder() {
        return new SubjectBuilder();
    }

    public static class SubjectBuilder {

        private Long id;
        private UUID uuid;
        private String name;
        private String description;
        private LocalDateTime created;

        public SubjectBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public SubjectBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public SubjectBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public SubjectBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public SubjectBuilder setCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Subject build() {
            return new Subject(this);
        }
    }
}
