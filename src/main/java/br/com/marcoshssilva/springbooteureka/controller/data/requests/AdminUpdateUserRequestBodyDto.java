package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public record AdminUpdateUserRequestBodyDto(@NotBlank String username, @NotNull String[] roles) implements Serializable {
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
