package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public record SpringSessionAttributesPK(
    @Column(name = "SESSION_PRIMARY_ID", length = 36, nullable = false)
    String sessionPrimaryId,

    @Column(name = "ATTRIBUTE_NAME", length = 200, nullable = false)
    String attributeName
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSessionAttributesPK that = (SpringSessionAttributesPK) o;
        return Objects.equals(sessionPrimaryId, that.sessionPrimaryId) && Objects.equals(attributeName, that.attributeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionPrimaryId, attributeName);
    }

    @Override
    public String toString() {
        return "SpringSessionAttributesPK{" +
                "sessionPrimaryId='" + sessionPrimaryId + '\'' +
                ", attributeName='" + attributeName + '\'' +
                '}';
    }
}
