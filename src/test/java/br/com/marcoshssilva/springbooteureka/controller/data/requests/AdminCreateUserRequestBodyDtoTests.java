package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminCreateUserRequestBodyDtoTests {
    @Test
    void testGetters() {
        UserRoles[] roles = {UserRoles.ADMIN};
        AdminCreateUserRequestBodyDto dto = new AdminCreateUserRequestBodyDto("admin", "pass", true, roles);

        assertEquals("admin", dto.username());
        assertEquals("pass", dto.password());
        assertTrue(dto.enabled());
        assertArrayEquals(roles, dto.roles());
    }

    @Test
    void testEqualsAndHashCode() {
        UserRoles[] roles1 = {UserRoles.ADMIN};
        UserRoles[] roles2 = {UserRoles.ADMIN};
        
        AdminCreateUserRequestBodyDto d1 = new AdminCreateUserRequestBodyDto("admin", "pass", true, roles1);
        AdminCreateUserRequestBodyDto d2 = new AdminCreateUserRequestBodyDto("admin", "pass", true, roles2);
        AdminCreateUserRequestBodyDto d3 = new AdminCreateUserRequestBodyDto("admin2", "pass", true, roles1);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
        assertNotEquals(d1, null);
    }

    @Test
    void testToString() {
        UserRoles[] roles = {UserRoles.ADMIN};
        AdminCreateUserRequestBodyDto dto = new AdminCreateUserRequestBodyDto("admin", "pass", true, roles);

        String text = dto.toString();
        assertTrue(text.contains("admin"));
        assertTrue(text.contains("pass"));
        assertTrue(text.contains("true"));
    }
}
