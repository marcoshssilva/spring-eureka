package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * JPA mapping for the Spring Session JDBC {@code SPRING_SESSION_ATTRIBUTES} table.
 * <p>
 * The foreign key to {@link SpringSession} is generated with {@code ON DELETE CASCADE} so that
 * deleting a session (which Spring Session does via plain JDBC) also removes its attribute rows.
 */
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Entity
@Table(name = "SPRING_SESSION_ATTRIBUTES")
public class SpringSessionAttributes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SpringSessionAttributesPK id;

    @MapsId("sessionPrimaryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SESSION_PRIMARY_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SpringSession session;

    @Lob
    @Column(name = "ATTRIBUTE_BYTES", nullable = false)
    private byte[] attributeBytes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSessionAttributes that = (SpringSessionAttributes) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
