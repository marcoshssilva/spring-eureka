package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record AdminResetPasswordRequestBodyDto(
        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 5, max = 32, message = "Must contain at least 5 characters and max 32")
        @Pattern(regexp = "^[A-Za-z0-9.-]{1,32}$", message = "Must contains only letters, numbers, dots and hyphen")
        String username,

        @NotBlank(message = "Field cannot be null or empty")
        @Size(min = 5, max = 64, message = "Must contain at least 5 characters and max 64")
        @Pattern(regexp = "^\\S{1,64}$", message = "Must contains only letters, numbers or special characters, except space")
        String newPassword

) implements Serializable { }
