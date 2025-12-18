package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "id.user", fetch = FetchType.EAGER)
    Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(enabled, user.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, enabled);
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", enabled=" + enabled + '}';
    }
}
