package com.epam.examinationsystem.core.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * The user entity
 */
public class User extends AbstractEntity {

    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Boolean isActivated;
    private final Role role;

    private User(UserBuilder builder) {
        super.id = builder.id;
        super.uuid = builder.uuid;
        super.created = builder.created;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.isActivated = builder.isActivated;
        this.role = builder.role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public Role getRole() {
        return role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(username, user.username)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(username)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("username", username)
                .append("password", password)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("isActivated", isActivated)
                .append("role", role.getName())
                .toString();
    }

    public static class UserBuilder {

        private Long id;
        private UUID uuid;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private Boolean isActivated;
        private Role role;
        private LocalDateTime created;

        public UserBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setIsActivated(Boolean isActivated) {
            this.isActivated = isActivated;
            return this;
        }

        public UserBuilder setRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder setCreated(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
