package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminUpdateUserRequestBodyDtoTests {
    @Test
    void testGetters() {
        UserRoles[] roles = {UserRoles.ADMIN};
        AdminUpdateUserRequestBodyDto dto = new AdminUpdateUserRequestBodyDto("user", roles);
        assertEquals("user", dto.username());
        assertArrayEquals(roles, dto.roles());
    }

    @Test
    void testEqualsAndHashCode() {
        UserRoles[] roles1 = {UserRoles.ADMIN};
        UserRoles[] roles2 = {UserRoles.ADMIN};
        AdminUpdateUserRequestBodyDto d1 = new AdminUpdateUserRequestBodyDto("user", roles1);
        AdminUpdateUserRequestBodyDto d2 = new AdminUpdateUserRequestBodyDto("user", roles2);
        AdminUpdateUserRequestBodyDto d3 = new AdminUpdateUserRequestBodyDto("user2", roles1);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
    }

    @Test
    void testToString() {
        UserRoles[] roles = {UserRoles.ADMIN};
        AdminUpdateUserRequestBodyDto dto = new AdminUpdateUserRequestBodyDto("user", roles);
        assertTrue(dto.toString().contains("user"));
    }
}
