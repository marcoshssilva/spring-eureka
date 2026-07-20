package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class RoleGroupPK implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "authority")
    private String role;

    public RoleGroupPK() {}

    public RoleGroupPK(Long groupId, String role) {
        this.groupId = groupId;
        this.role = role;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleGroupPK{" +
                "groupId=" + groupId +
                ", role='" + role + '\'' +
                '}';
    }

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
}
