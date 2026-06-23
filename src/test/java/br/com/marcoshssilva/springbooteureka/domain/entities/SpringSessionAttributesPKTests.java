package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpringSessionAttributesPKTests {
    @Test
    void testGettersAndSetters() {
        SpringSessionAttributesPK pk = new SpringSessionAttributesPK();
        pk.setSessionPrimaryId("p1");
        pk.setAttributeName("attr");

        assertEquals("p1", pk.getSessionPrimaryId());
        assertEquals("attr", pk.getAttributeName());
    }

    @Test
    void testEqualsAndHashCode() {
        SpringSessionAttributesPK pk1 = new SpringSessionAttributesPK("p1", "attr1");
        SpringSessionAttributesPK pk2 = new SpringSessionAttributesPK("p1", "attr1");
        SpringSessionAttributesPK pk3 = new SpringSessionAttributesPK("p1", "attr2");

        assertEquals(pk1, pk2);
        assertEquals(pk1.hashCode(), pk2.hashCode());
        assertNotEquals(pk1, pk3);
        assertNotEquals(pk1, null);
        assertNotEquals(pk1, "string");
    }
}
