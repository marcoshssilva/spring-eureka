package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "authorities")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RolePK id;

    public Role() {}

    public Role(RolePK id) {
        this.id = id;
    }

    public RolePK getId() {
        return id;
    }

    public void setId(RolePK id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + '}';
    }
}
