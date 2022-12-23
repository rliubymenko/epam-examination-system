package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEntity implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 1234567L;

    private Integer id;
    private UUID uuid;

    protected AbstractEntity() {
        this.uuid = UUID.randomUUID();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("uuid", uuid)
                .toString();
    }
}
