package com.github.marcoshssilva.eureka.controller.data.requests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserChangePasswordRequestBodyDtoTests {
    @Test
    void testGetters() {
        UserChangePasswordRequestBodyDto dto = new UserChangePasswordRequestBodyDto("new", "old");
        assertEquals("old", dto.oldPassword());
        assertEquals("new", dto.newPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        UserChangePasswordRequestBodyDto d1 = new UserChangePasswordRequestBodyDto("new", "old");
        UserChangePasswordRequestBodyDto d2 = new UserChangePasswordRequestBodyDto("new", "old");
        UserChangePasswordRequestBodyDto d3 = new UserChangePasswordRequestBodyDto("new", "old2");

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotEquals(d1, d3);
    }

    @Test
    void testToString() {
        UserChangePasswordRequestBodyDto dto = new UserChangePasswordRequestBodyDto("new", "old");
        assertTrue(dto.toString().contains("old"));
        assertTrue(dto.toString().contains("new"));
    }
}
