package br.com.marcoshssilva.springbooteureka.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * JPA mapping for the Spring Session JDBC {@code SPRING_SESSION} table.
 * <p>
 * The table is created by Hibernate ({@code ddl-auto: update}) so it works on any JDBC database,
 * while Spring Session ({@code initialize-schema: never}) only reads/writes through it.
 */
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
@lombok.Data
@Entity
@Table(name = "SPRING_SESSION", indexes = {
        @Index(name = "SPRING_SESSION_IX1", columnList = "SESSION_ID", unique = true),
        @Index(name = "SPRING_SESSION_IX2", columnList = "EXPIRY_TIME"),
        @Index(name = "SPRING_SESSION_IX3", columnList = "PRINCIPAL_NAME")
})
public class SpringSession implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PRIMARY_ID", length = 36, nullable = false)
    private String primaryId;

    @Column(name = "SESSION_ID", length = 36, nullable = false)
    private String sessionId;

    @Column(name = "CREATION_TIME", nullable = false)
    private Long creationTime;

    @Column(name = "LAST_ACCESS_TIME", nullable = false)
    private Long lastAccessTime;

    @Column(name = "MAX_INACTIVE_INTERVAL", nullable = false)
    private Integer maxInactiveInterval;

    @Column(name = "EXPIRY_TIME", nullable = false)
    private Long expiryTime;

    @Column(name = "PRINCIPAL_NAME", length = 100)
    private String principalName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpringSession that = (SpringSession) o;
        return Objects.equals(primaryId, that.primaryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryId);
    }
}
