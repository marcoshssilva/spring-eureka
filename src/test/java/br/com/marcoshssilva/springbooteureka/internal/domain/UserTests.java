package br.com.marcoshssilva.springbooteureka.internal.domain;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {
    @DisplayName("Must convert roles to SimpleGrantedAuthority")
    @Test
    void testGetAuthorities() {
        User user = new User("john", "123", true, null);
        Role adminRole = new Role(new RolePK(user, "ROLE_ADMIN"));
        Role userRole = new Role(new RolePK(user, "ROLE_USER"));
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(userRole);

        user.setRoles(roles);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertEquals(2, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @DisplayName("Must return false if object is different class or comparable with null")
    @Test
    void testEqualsNullAndOtherClass() {
        User user = new User("john", "123",  Boolean.TRUE, null);
        assertNotEquals(null, user);
        assertNotEquals("any other class", user);
    }

    @DisplayName("Must compare identical users as true")
    @Test
    void testEqualsTrue() {
        User u1 = new User("john", "123", true, null);
        User u2 = new User("john", "123", true, null);

        assertEquals(u1, u2);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @DisplayName("Must compare not identical users as false")
    @Test
    void testEqualsFalse() {
        User u1 = new User("john", "123", true, null);
        User u2 = new User("john", "456", true, null);

        assertNotEquals(u1, u2);
    }

    @DisplayName("Method toString() must contains username, password and enabled")
    @Test
    void testToString() {
        User user = new User("john", "123", true, null);

        String text = user.toString();

        assertTrue(text.contains("john"));
        assertTrue(text.contains("123"));
        assertTrue(text.contains("true"));
    }

    @DisplayName("Method isEnabled() must reflect property enabled")
    @Test
    void testIsEnabled() {
        User enabledUser = new User("john", "123", true, null);
        User disabledUser = new User("mary", "123", false, null);

        assertTrue(enabledUser.isEnabled());
        assertFalse(disabledUser.isEnabled());
    }
}
