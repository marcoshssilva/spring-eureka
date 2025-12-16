package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record AdminResetPasswordRequestBodyDto(@NotBlank String username, @NotBlank String newPassword) implements Serializable { }
