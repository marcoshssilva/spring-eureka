package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
// @Entity
// @Table(name = "group_authorities")
public class RoleGroup {
    @EmbeddedId
    RoleGroupPK id;

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
