package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoleGroupTests {
    @Test
    void testGettersAndSetters() {
        RoleGroupPK pk = new RoleGroupPK(1L, "ROLE_ADMIN");
        RoleGroup roleGroup = new RoleGroup();
        roleGroup.setId(pk);

        assertEquals(pk, roleGroup.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        RoleGroupPK pk1 = new RoleGroupPK(1L, "ROLE_ADMIN");
        RoleGroupPK pk2 = new RoleGroupPK(2L, "ROLE_USER");

        RoleGroup r1 = new RoleGroup(pk1);
        RoleGroup r2 = new RoleGroup(pk1);
        RoleGroup r3 = new RoleGroup(pk2);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
        assertNotEquals(r1, r3);
        assertNotEquals(r1, null);
        assertNotEquals(r1, "string");
    }
}
