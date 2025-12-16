package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import java.io.Serializable;
import java.util.Objects;

public record AdminChangePasswordRequestBodyDto(String username, String newPassword, String oldPassword) implements Serializable {
    @Override
    public String toString() {
        return "AdminChangePasswordRequestBodyDto{" +
                "username='" + username + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminChangePasswordRequestBodyDto that = (AdminChangePasswordRequestBodyDto) o;
        return Objects.equals(username, that.username) && Objects.equals(newPassword, that.newPassword) && Objects.equals(oldPassword, that.oldPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, newPassword, oldPassword);
    }
}
