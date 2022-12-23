package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserTest {

    private User user;
    private Test test;
    private Boolean isSelected;
    private Boolean isCompleted;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTest userTest = (UserTest) o;
        return new EqualsBuilder()
                .append(user.getUsername(), userTest.user.getUsername())
                .append(test.getName(), userTest.test.getName())
                .append(isSelected, userTest.isSelected)
                .append(isCompleted, userTest.isCompleted)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(user.getUsername())
                .append(test.getName())
                .append(isSelected)
                .append(isCompleted)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user", user.getUsername())
                .append("test", test.getName())
                .append("isSelected", isSelected)
                .append("isCompleted", isCompleted)
                .toString();
    }
}
