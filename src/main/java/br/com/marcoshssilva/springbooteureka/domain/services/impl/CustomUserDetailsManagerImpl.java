package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

public class CustomUserDetailsManagerImpl extends JdbcUserDetailsManager {
    private static final String MSG_USER_NOT_FOUND = "User not found.";

    private final UserManagementService userManagementService;

    public CustomUserDetailsManagerImpl(DataSource dataSource, UserManagementService userManagementService) {
        super(dataSource);
        this.userManagementService = userManagementService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userManagementService.getUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException(MSG_USER_NOT_FOUND, e);
        }
    }
}
