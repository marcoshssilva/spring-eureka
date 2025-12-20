package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.repositories.RoleRepository;
import br.com.marcoshssilva.springbooteureka.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class UserManagementServiceImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserManagementServiceImpl userManagementService;

    @BeforeEach
    void setup() {
        userManagementService = new UserManagementServiceImpl(userRepository, roleRepository, passwordEncoder);
    }

    @DisplayName("Must call createUser() and if not exists create with sucess")
    @Test
    void createUser() {
        assertDoesNotThrow(() -> userManagementService.createUser("john.doe", "P@ssw0rd", Boolean.TRUE, new String[]{ "CLIENT", "READER" }));
        assertThrows(BusinessException.class, () -> userManagementService.createUser("john.doe", "P@ssw0rd", Boolean.TRUE, new String[]{ "CLIENT", "READER" }));
    }
}
