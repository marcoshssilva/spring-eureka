package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public record MemberGroupPK(
    @Column(name = "group_id")
    Long groupId,
    @Column(name = "username")
    String username
) implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberGroupPK that = (MemberGroupPK) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, username);
    }

    @Override
    public String toString() {
        return "MemberGroupPK{" +
                "groupId=" + groupId +
                ", username='" + username + '\'' +
                '}';
    }
}
