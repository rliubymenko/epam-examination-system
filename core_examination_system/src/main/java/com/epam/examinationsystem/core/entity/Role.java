package com.epam.examinationsystem.core.entity;

import com.epam.examinationsystem.core.enumeration.UserType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Role extends AbstractEntity {

    private UserType name;

    public UserType getName() {
        return name;
    }

    public void setName(UserType name) {
        this.name = name;
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
}
