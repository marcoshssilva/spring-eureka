package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpringSessionAttributesTests {
    @Test
    void testGettersAndSetters() {
        SpringSessionAttributesPK pk = new SpringSessionAttributesPK("p1", "attr");
        SpringSession session = new SpringSession();
        session.setPrimaryId("p1");
        
        byte[] bytes = new byte[]{1, 2, 3};
        
        SpringSessionAttributes attrs = new SpringSessionAttributes();
        attrs.setId(pk);
        attrs.setSession(session);
        attrs.setAttributeBytes(bytes);

        assertEquals(pk, attrs.getId());
        assertEquals(session, attrs.getSession());
        assertArrayEquals(bytes, attrs.getAttributeBytes());
    }

    @Test
    void testEqualsAndHashCode() {
        SpringSessionAttributesPK pk1 = new SpringSessionAttributesPK("p1", "attr1");
        SpringSessionAttributesPK pk2 = new SpringSessionAttributesPK("p1", "attr1");
        SpringSessionAttributesPK pk3 = new SpringSessionAttributesPK("p1", "attr2");

        SpringSessionAttributes a1 = new SpringSessionAttributes();
        a1.setId(pk1);
        SpringSessionAttributes a2 = new SpringSessionAttributes();
        a2.setId(pk2);
        SpringSessionAttributes a3 = new SpringSessionAttributes();
        a3.setId(pk3);

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, a3);
        assertNotEquals(a1, null);
        assertNotEquals(a1, "string");
    }
}
