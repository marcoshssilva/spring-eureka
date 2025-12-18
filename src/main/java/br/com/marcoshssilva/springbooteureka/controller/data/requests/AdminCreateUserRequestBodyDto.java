package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public record AdminCreateUserRequestBodyDto(
        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 5, max = 32, message = "Must contain at least 5 characters and max 32")
        @Pattern(regexp = "^[A-Za-z0-9.-]{1,32}$", message = "Must contains only letters, numbers, dots and hyphen")
        String username,

        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 8, max = 64)
        @Pattern(regexp = "^\\S{8,64}$", message = "Must contains only letters, numbers or special characters, except space")
        String password,

        @NotNull(message = "Field cannot be null or empty")
        Boolean enabled,

        @NotNull(message = "Field cannot be null or empty")
        @NotEmpty(message = "Field cannot be null or empty")
        @Size(min = 1, max = 12, message = "Must contain at least 1 and max 12")
        UserRoles[] roles

) implements Serializable {
    @Override
    public String toString() {
        return "AdminCreateUserRequestBodyDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminCreateUserRequestBodyDto that = (AdminCreateUserRequestBodyDto) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(enabled, that.enabled) && Arrays.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, password, enabled);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }
}
