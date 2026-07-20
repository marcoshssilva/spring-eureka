package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public record RolePK(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    User user,

    @Column(name = "authority", nullable = false)
    String authority
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePK rolePK = (RolePK) o;
        return Objects.equals(user != null ? user.getUsername() : null, rolePK.user() != null ? rolePK.user().getUsername() : null) && Objects.equals(authority, rolePK.authority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user != null ? user.getUsername() : null, authority);
    }

    @Override
    public String toString() {
        return "RolePK{" + "username=" + (user != null ? user.getUsername() : "null") + ", authority='" + authority + '\'' + '}';
    }
}
