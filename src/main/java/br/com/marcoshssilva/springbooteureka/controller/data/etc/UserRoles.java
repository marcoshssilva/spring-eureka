package br.com.marcoshssilva.springbooteureka.controller.data.etc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoles {
    ADMIN("ADMIN"),
    READER("READER"),
    METRICS("METRICS"),
    CLIENT("CLIENT");

    private final String authority;
}
