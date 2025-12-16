package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public record AdminUpdateUserRequestBodyDto(
        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 5, max = 32, message = "Must contain at least 5 characters and max 32")
        @Pattern(regexp = "^[A-Za-z0-9.-]{1,32}$", message = "Must contains only letters, numbers, dots and hyphen")
        String username,

        @NotNull(message = "Field cannot be null or empty")
        @NotEmpty(message = "Field cannot be null or empty")
        @Size(min = 1, max = 12, message = "Must contain at least 1 and max 12")
        String[] roles

) implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminUpdateUserRequestBodyDto that = (AdminUpdateUserRequestBodyDto) o;
        return Objects.equals(username, that.username) && Arrays.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }

    @Override
    public String toString() {
        return "AdminUpdateUserRequestBodyDto{" +
                "username='" + username + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
