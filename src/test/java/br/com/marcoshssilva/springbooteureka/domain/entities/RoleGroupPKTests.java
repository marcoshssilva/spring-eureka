package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleGroupPKTests {
    @Test
    void testGettersAndSetters() {
        RoleGroupPK pk = new RoleGroupPK();
        pk.setGroupId(1L);
        pk.setRole("ROLE_ADMIN");

        assertEquals(1L, pk.getGroupId());
        assertEquals("ROLE_ADMIN", pk.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        RoleGroupPK pk1 = new RoleGroupPK(1L, "ROLE_ADMIN");
        RoleGroupPK pk2 = new RoleGroupPK(1L, "ROLE_ADMIN");
        RoleGroupPK pk3 = new RoleGroupPK(2L, "ROLE_ADMIN");

        assertEquals(pk1, pk2);
        assertEquals(pk1.hashCode(), pk2.hashCode());
        assertNotEquals(pk1, pk3);
        assertNotEquals(pk1, null);
        assertNotEquals(pk1, "string");
    }
}
