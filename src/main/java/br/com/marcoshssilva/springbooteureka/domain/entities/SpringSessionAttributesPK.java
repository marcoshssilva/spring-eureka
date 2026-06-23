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
public class SpringSessionAttributesPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "SESSION_PRIMARY_ID", length = 36, nullable = false)
    private String sessionPrimaryId;

    @Column(name = "ATTRIBUTE_NAME", length = 200, nullable = false)
    private String attributeName;

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
}
