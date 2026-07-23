package com.github.marcoshssilva.eureka.domain.entities;

import jakarta.persistence.EmbeddedId;
import java.util.Objects;

// @Entity
// @Table(name = "group_authorities")
public class RoleGroup {
    @EmbeddedId
    RoleGroupPK id;

    public RoleGroup() {}

    public RoleGroup(RoleGroupPK id) {
        this.id = id;
    }

    public RoleGroupPK getId() {
        return id;
    }

    public void setId(RoleGroupPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleGroup roleGroup = (RoleGroup) o;
        return Objects.equals(id, roleGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
