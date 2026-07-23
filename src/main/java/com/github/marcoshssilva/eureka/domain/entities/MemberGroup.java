package com.github.marcoshssilva.eureka.domain.entities;

import jakarta.persistence.EmbeddedId;
import java.util.Objects;

// @Entity
// @Table(name = "group_members")
public class MemberGroup {
    @EmbeddedId
    private MemberGroupPK id;

    public MemberGroup() {}

    public MemberGroup(MemberGroupPK id) {
        this.id = id;
    }

    public MemberGroupPK getId() {
        return id;
    }

    public void setId(MemberGroupPK id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MemberGroup{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberGroup that = (MemberGroup) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
