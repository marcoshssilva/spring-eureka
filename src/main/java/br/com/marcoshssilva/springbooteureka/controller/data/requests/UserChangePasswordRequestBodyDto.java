package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

public record UserChangePasswordRequestBodyDto(
        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 8, max = 64, message = "Must contain at least 8 characters and max 64")
        @Pattern(regexp = "^\\S{8,64}$", message = "Must contains only letters, numbers or special characters, except space")
        String newPassword,

        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 5, max = 64, message = "Must contain at least 5 characters and max 64")
        @Pattern(regexp = "^\\S{5,64}$", message = "Must contains only letters, numbers or special characters, except space")
        String oldPassword

) implements Serializable {
    @Override
    public String toString() {
        return "AdminChangePasswordRequestBodyDto{" +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChangePasswordRequestBodyDto that = (UserChangePasswordRequestBodyDto) o;
        return Objects.equals(newPassword, that.newPassword) && Objects.equals(oldPassword, that.oldPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newPassword, oldPassword);
    }
}
