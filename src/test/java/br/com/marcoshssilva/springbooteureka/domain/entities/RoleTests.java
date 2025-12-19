package br.com.marcoshssilva.springbooteureka.domain.entities;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTests {
    @DisplayName("Must return true if authority is the same")
    @Test
    void testEqualsTrue() {
        RolePK pk1 = new RolePK(new User("ADMIN", "123", Boolean.TRUE, null), "ADMIN");
        RolePK pk2 = new RolePK(new User("ADMIN", "123", Boolean.TRUE, null), "ADMIN");

        Role r1 = new Role(pk1);
        Role r2 = new Role(pk2);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @DisplayName("Must return false if authority is not the same")
    @Test
    void testEqualsFalse() {
        RolePK pk1 = new RolePK(new User("ADMIN", "123", Boolean.TRUE, null), "ADMIN");
        RolePK pk2 = new RolePK(new User("ADMIN", "123", Boolean.TRUE, null), "USER");

        Role r1 = new Role(pk1);
        Role r2 = new Role(pk2);

        assertNotEquals(r1, r2);
    }

    @DisplayName("Must return false if object is different class or comparable with null")
    @Test
    void testEqualsNullAndOtherClass() {
        RolePK pk = new RolePK(new User("john", "123", Boolean.TRUE, null), "ADMIN");
        Role role = new Role(pk);

        assertNotEquals(null, role);
        assertNotEquals("ADMIN", role);
    }

    @DisplayName("Method hashCode() must be same for RolePK when atributes are equals")
    @Test
    void testHashCodeConsistency() {
        RolePK pk = new RolePK(new User("john", "123", Boolean.TRUE, null), "ADMIN");

        Role r1 = new Role(pk);
        Role r2 = new Role(pk);

        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @DisplayName("Method toString() must contains RolePK")
    @Test
    void testToString() {
        RolePK pk = new RolePK(new User("ADMIN", "123", Boolean.TRUE, null), "ADMIN");
        Role role = new Role(pk);

        String text = role.toString();

        assertTrue(text.contains("Role"));
        assertTrue(text.contains("id="));
        assertTrue(text.contains("ADMIN"));
    }

}
