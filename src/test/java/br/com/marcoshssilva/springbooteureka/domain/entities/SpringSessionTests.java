package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpringSessionTests {
    @Test
    void testGettersAndSetters() {
        SpringSession session = new SpringSession();
        session.setPrimaryId("p1");
        session.setSessionId("s1");
        session.setCreationTime(100L);
        session.setLastAccessTime(200L);
        session.setMaxInactiveInterval(1800);
        session.setExpiryTime(300L);
        session.setPrincipalName("admin");

        assertEquals("p1", session.getPrimaryId());
        assertEquals("s1", session.getSessionId());
        assertEquals(100L, session.getCreationTime());
        assertEquals(200L, session.getLastAccessTime());
        assertEquals(1800, session.getMaxInactiveInterval());
        assertEquals(300L, session.getExpiryTime());
        assertEquals("admin", session.getPrincipalName());
    }

    @Test
    void testEqualsAndHashCode() {
        SpringSession s1 = new SpringSession();
        s1.setPrimaryId("p1");
        SpringSession s2 = new SpringSession();
        s2.setPrimaryId("p1");
        SpringSession s3 = new SpringSession();
        s3.setPrimaryId("p2");

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertNotEquals(s1, s3);
        assertNotEquals(s1, null);
        assertNotEquals(s1, "string");
    }
}
