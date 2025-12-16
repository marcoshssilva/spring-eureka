package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public record AdminCreateUserRequestBodyDto(@NotBlank String username, @NotBlank String password, @NotNull Boolean enabled, @NotNull String[] roles) implements Serializable {
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
