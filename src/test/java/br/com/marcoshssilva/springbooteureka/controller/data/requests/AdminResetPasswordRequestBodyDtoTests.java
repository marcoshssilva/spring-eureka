package br.com.marcoshssilva.springbooteureka.controller.data.requests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdminResetPasswordRequestBodyDtoTests {
    @Test
    void testGetters() {
        AdminResetPasswordRequestBodyDto dto = new AdminResetPasswordRequestBodyDto("admin", "newPass");
        assertEquals("admin", dto.username());
        assertEquals("newPass", dto.newPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        AdminResetPasswordRequestBodyDto d1 = new AdminResetPasswordRequestBodyDto("admin", "pass");
        AdminResetPasswordRequestBodyDto d2 = new AdminResetPasswordRequestBodyDto("admin", "pass");
        AdminResetPasswordRequestBodyDto d3 = new AdminResetPasswordRequestBodyDto("admin2", "pass");

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
    }
}
