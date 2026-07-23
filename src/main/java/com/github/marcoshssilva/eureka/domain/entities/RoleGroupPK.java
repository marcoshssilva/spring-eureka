package com.github.marcoshssilva.eureka.domain.entities;

import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record RoleGroupPK(
    @Column(name = "group_id")
    Long groupId,
    @Column(name = "authority")
    String role
) implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleGroupPK that = (RoleGroupPK) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, role);
    }

    @Override
    public String toString() {
        return "RoleGroupPK{" +
                "groupId=" + groupId +
                ", role='" + role + '\'' +
                '}';
    }
}
