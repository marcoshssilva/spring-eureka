package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RolePKTests {
    @Test
    void testGettersAndSetters() {
        User user = new User("john", "123", true, null);
        RolePK pk = new RolePK();
        pk.setUser(user);
        pk.setAuthority("ROLE_ADMIN");

        assertEquals(user, pk.getUser());
        assertEquals("ROLE_ADMIN", pk.getAuthority());
    }

    @Test
    void testEqualsAndHashCode() {
        User u1 = new User("john", "123", true, null);
        User u2 = new User("john", "456", true, null);
        
        RolePK pk1 = new RolePK(u1, "ROLE_ADMIN");
        RolePK pk2 = new RolePK(u2, "ROLE_ADMIN"); // Same username
        RolePK pk3 = new RolePK(u1, "ROLE_USER");

        assertEquals(pk1, pk2);
        assertEquals(pk1.hashCode(), pk2.hashCode());
        assertNotEquals(pk1, pk3);
        assertNotEquals(pk1, null);
        assertNotEquals(pk1, "string");
    }

    @Test
    void testToString() {
        User user = new User("john", "123", true, null);
        RolePK pk = new RolePK(user, "ROLE_ADMIN");

        String text = pk.toString();
        assertTrue(text.contains("john"));
        assertTrue(text.contains("ROLE_ADMIN"));
    }
}
