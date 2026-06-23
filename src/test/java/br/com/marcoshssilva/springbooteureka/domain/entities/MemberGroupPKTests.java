package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberGroupPKTests {
    @Test
    void testGettersAndSetters() {
        MemberGroupPK pk = new MemberGroupPK();
        pk.setGroupId(1L);
        pk.setUsername("user");

        assertEquals(1L, pk.getGroupId());
        assertEquals("user", pk.getUsername());
    }

    @Test
    void testEqualsAndHashCode() {
        MemberGroupPK pk1 = new MemberGroupPK(1L, "user");
        MemberGroupPK pk2 = new MemberGroupPK(1L, "user");
        MemberGroupPK pk3 = new MemberGroupPK(2L, "user");

        assertEquals(pk1, pk2);
        assertEquals(pk1.hashCode(), pk2.hashCode());
        assertNotEquals(pk1, pk3);
        assertNotEquals(pk1, null);
        assertNotEquals(pk1, "string");
    }
}
