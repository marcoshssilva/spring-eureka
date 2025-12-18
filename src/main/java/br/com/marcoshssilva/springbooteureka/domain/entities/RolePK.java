package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Embeddable
public class RolePK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePK rolePK = (RolePK) o;
        return Objects.equals(user, rolePK.user) && Objects.equals(authority, rolePK.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, authority);
    }

    @Override
    public String toString() {
        return "RolePK{" + "username=" + user.getUsername() + ", authority='" + authority + '\'' + '}';
    }
}
