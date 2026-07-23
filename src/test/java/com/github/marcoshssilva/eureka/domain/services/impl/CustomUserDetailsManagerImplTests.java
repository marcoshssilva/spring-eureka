package com.github.marcoshssilva.eureka.domain.services.impl;

import com.github.marcoshssilva.eureka.domain.entities.User;
import com.github.marcoshssilva.eureka.domain.exceptions.BusinessException;
import com.github.marcoshssilva.eureka.domain.services.UserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsManagerImplTests {

    @Test
    void testLoadUserByUsername() throws BusinessException {
        DataSource dataSource = mock(DataSource.class);
        UserManagementService userManagementService = mock(UserManagementService.class);
        CustomUserDetailsManagerImpl manager = new CustomUserDetailsManagerImpl(dataSource, userManagementService);

        User user = new User("john", "pass", true, null);
        when(userManagementService.getUserByUsername("john")).thenReturn(user);

        UserDetails result = manager.loadUserByUsername("john");
        assertEquals("john", result.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() throws BusinessException {
        DataSource dataSource = mock(DataSource.class);
        UserManagementService userManagementService = mock(UserManagementService.class);
        CustomUserDetailsManagerImpl manager = new CustomUserDetailsManagerImpl(dataSource, userManagementService);

        when(userManagementService.getUserByUsername("invalid")).thenThrow(new BusinessException("Not found"));

        assertThrows(UsernameNotFoundException.class, () -> manager.loadUserByUsername("invalid"));
    }
}
