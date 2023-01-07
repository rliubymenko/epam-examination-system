package com.epam.examinationsystem.core.entity;

import com.epam.examinationsystem.core.enumeration.UserType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class Role extends AbstractEntity {

    private final UserType name;

    public Role(RoleBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        this.name = builder.name;
    }

    public UserType getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(name, role.name)
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
                .append("name", name.toString())
                .toString();
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public static class RoleBuilder {

        private Long id;
        private UUID uuid;
        private UserType name;

        public RoleBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public RoleBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public RoleBuilder setName(UserType name) {
            this.name = name;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }
}
