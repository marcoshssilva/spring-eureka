package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import jakarta.validation.constraints.NotBlank;

public record AdminUpdatePasswordRequestBodyDto(@NotBlank String username, @NotBlank String newPassword) { }
