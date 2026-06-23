package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberGroupTests {
    @Test
    void testGettersAndSetters() {
        MemberGroupPK pk = new MemberGroupPK(1L, "user");
        MemberGroup memberGroup = new MemberGroup();
        memberGroup.setId(pk);

        assertEquals(pk, memberGroup.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        MemberGroupPK pk1 = new MemberGroupPK(1L, "user");
        MemberGroupPK pk2 = new MemberGroupPK(2L, "user2");
        
        MemberGroup m1 = new MemberGroup(pk1);
        MemberGroup m2 = new MemberGroup(pk1);
        MemberGroup m3 = new MemberGroup(pk2);

        assertEquals(m1, m2);
        assertEquals(m1.hashCode(), m2.hashCode());
        assertNotEquals(m1, m3);
        assertNotEquals(m1, null);
        assertNotEquals(m1, "string");
    }
}
