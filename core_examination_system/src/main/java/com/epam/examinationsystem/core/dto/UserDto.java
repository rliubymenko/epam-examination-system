package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Optional;

public class UserDto {

    private final String uuid;
    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String role;

    private UserDto(UserDtoBuilder builder) {
        this.uuid = builder.uuid;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
    }

    public String getUuid() {
        return uuid;
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

    public String getRole() {
        return role;
    }

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return new EqualsBuilder()
                .append(uuid, userDto.uuid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("username", username)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .toString();
    }

    public static class UserDtoBuilder {

        private String uuid;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private String role;

        public UserDtoBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public UserDtoBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDtoBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserDtoBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

        public Optional<UserDto> fromUser(Optional<User> maybeUser) {
            return maybeUser.map(user -> this
                    .setUuid(user.getUuid().toString())
                    .setUsername(user.getUsername())
                    .setPassword(user.getPassword())
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setRole(user.getRole().getName().toString())
                    .build());
        }
    }
}
