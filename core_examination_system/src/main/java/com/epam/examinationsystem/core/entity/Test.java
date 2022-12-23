package com.epam.examinationsystem.core.entity;

import com.epam.examinationsystem.core.enumeration.TestComplexity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Test extends AbstractEntity {

    private String name;
    private String description;
    private TestComplexity complexity;
    private Integer duration;
    private Integer totalAttemptNumber;
    private Subject subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestComplexity getComplexity() {
        return complexity;
    }

    public void setComplexity(TestComplexity complexity) {
        this.complexity = complexity;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTotalAttemptNumber() {
        return totalAttemptNumber;
    }

    public void setTotalAttemptNumber(Integer totalAttemptNumber) {
        this.totalAttemptNumber = totalAttemptNumber;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test test)) return false;
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
                .append("name", name)
                .append("description", description)
                .append("complexity", complexity.toString())
                .append("duration", duration)
                .append("totalAttemptNumber", totalAttemptNumber)
                .append("subject", subject.getName())
                .toString();
    }
}