package br.com.marcoshssilva.springbooteureka.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GroupTests {
    @Test
    void testGettersAndSetters() {
        Group group = new Group();
        group.setId(1L);
        group.setGroupName("ADMINS");

        assertEquals(1L, group.getId());
        assertEquals("ADMINS", group.getGroupName());
    }

    @Test
    void testEqualsAndHashCode() {
        Group g1 = new Group(1L, "ADMINS");
        Group g2 = new Group(1L, "ADMINS");
        Group g3 = new Group(2L, "USERS");

        assertEquals(g1, g2);
        assertEquals(g1.hashCode(), g2.hashCode());
        assertNotEquals(g1, g3);
        assertNotEquals(g1, null);
        assertNotEquals(g1, "string");
    }

    @Test
    void testNoArgsConstructor() {
        Group group = new Group();
        assertNotNull(group);
    }
}
