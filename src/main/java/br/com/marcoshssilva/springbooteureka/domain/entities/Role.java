package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Entity
@Table(name = "authorities")
public class Role {
    @Id
    private String username;
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role authority1 = (Role) o;
        return Objects.equals(username, authority1.username) && Objects.equals(authority, authority1.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authority);
    }
}
