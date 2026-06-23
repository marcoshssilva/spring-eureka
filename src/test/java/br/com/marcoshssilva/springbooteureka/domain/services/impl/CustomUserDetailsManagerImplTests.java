package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomUserDetailsManagerImplTests {

    @Test
    void testLoadUserByUsername() throws BusinessException {
        DataSource dataSource = Mockito.mock(DataSource.class);
        UserManagementService userManagementService = Mockito.mock(UserManagementService.class);
        CustomUserDetailsManagerImpl manager = new CustomUserDetailsManagerImpl(dataSource, userManagementService);

        User user = new User("john", "pass", true, null);
        when(userManagementService.getUserByUsername("john")).thenReturn(user);

        UserDetails result = manager.loadUserByUsername("john");
        assertEquals("john", result.getUsername());
    }

    @Test
    void testLoadUserByUsernameNotFound() throws BusinessException {
        DataSource dataSource = Mockito.mock(DataSource.class);
        UserManagementService userManagementService = Mockito.mock(UserManagementService.class);
        CustomUserDetailsManagerImpl manager = new CustomUserDetailsManagerImpl(dataSource, userManagementService);

        when(userManagementService.getUserByUsername("invalid")).thenThrow(new BusinessException("Not found"));

        assertThrows(UsernameNotFoundException.class, () -> manager.loadUserByUsername("invalid"));
    }
}
