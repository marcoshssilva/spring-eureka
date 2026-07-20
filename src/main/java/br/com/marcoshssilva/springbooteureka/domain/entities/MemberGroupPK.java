package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberGroupPK implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "username")
    private String username;

    public MemberGroupPK() {}

    public MemberGroupPK(Long groupId, String username) {
        this.groupId = groupId;
        this.username = username;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "MemberGroupPK{" +
                "groupId=" + groupId +
                ", username='" + username + '\'' +
                '}';
    }

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
