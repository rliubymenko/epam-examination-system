package com.epam.examinationsystem.core.dto;

import com.epam.examinationsystem.core.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * UserDto for the user entity.
 */
public class UserDto extends AbstractDto {

    private final String username;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Boolean isActivated;
    private final String role;

    private UserDto(UserDtoBuilder builder) {
        super.uuid = builder.uuid;
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

    public String getRole() {
        return role;
    }

    public Boolean getIsActivated() {
        return isActivated;
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
                .append("isActivated", isActivated)
                .append("role", role)
                .toString();
    }

    public static class UserDtoBuilder {

        private String uuid;
        private String username;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private Boolean isActivated;
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

        public UserDtoBuilder setIsActivated(Boolean isActivated) {
            this.isActivated = isActivated;
            return this;
        }

        public UserDtoBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

        public UserDto fromUser(User user) {
            return this
                    .setUuid(user.getUuid().toString())
                    .setUsername(user.getUsername())
                    .setPassword(user.getPassword())
                    .setEmail(user.getEmail())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setIsActivated(user.getIsActivated())
                    .setRole(user.getRole().getName().toString())
                    .build();
        }
    }
}
