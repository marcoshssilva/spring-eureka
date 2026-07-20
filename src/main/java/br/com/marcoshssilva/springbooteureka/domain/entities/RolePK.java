package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RolePK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name = "authority", nullable = false)
    private String authority;

    public RolePK() {}

    public RolePK(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolePK rolePK = (RolePK) o;
        return Objects.equals(user != null ? user.getUsername() : null, rolePK.user != null ? rolePK.user.getUsername() : null) && Objects.equals(authority, rolePK.authority);
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
