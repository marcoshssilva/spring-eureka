package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
public class RoleGroupPK implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "authority")
    private String role;

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
