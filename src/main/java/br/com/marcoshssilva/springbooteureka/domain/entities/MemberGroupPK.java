package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Embeddable
public class MemberGroupPK implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "username")
    private String username;

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
}
