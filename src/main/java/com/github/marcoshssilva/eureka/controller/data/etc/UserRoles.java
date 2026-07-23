package com.github.marcoshssilva.eureka.controller.data.etc;

public enum UserRoles {
    ADMIN("ADMIN"),
    READER("READER"),
    METRICS("METRICS"),
    CLIENT("CLIENT");

    private final String authority;

    UserRoles(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
