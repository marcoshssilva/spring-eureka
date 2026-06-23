package br.com.marcoshssilva.springbooteureka.domain.services.impl;

import br.com.marcoshssilva.springbooteureka.domain.exceptions.BusinessException;
import br.com.marcoshssilva.springbooteureka.domain.repositories.RoleRepository;
import br.com.marcoshssilva.springbooteureka.domain.repositories.UserRepository;
import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Transactional
class UserManagementServiceImplTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManagementService userManagementService;

    @MockBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @BeforeEach
    void setup() {
        roleRepository.deleteAll();
        userRepository.deleteAll();
    }

    @DisplayName("Must call createUser() and if not exists create with sucess")
    @Test
    void createUser() {
        assertDoesNotThrow(() -> userManagementService.createUser("john.doe", "P@ssw0rd", Boolean.TRUE, new String[]{ "CLIENT", "READER" }));
        assertThrows(BusinessException.class, () -> userManagementService.createUser("john.doe", "P@ssw0rd", Boolean.TRUE, new String[]{ "CLIENT", "READER" }));
    }

    @Test
    void testResetPassword() throws BusinessException {
        userManagementService.createUser("reset.user", "oldPass", true, new String[]{"CLIENT"});
        assertDoesNotThrow(() -> userManagementService.resetPasswordFromUsername("reset.user", "newPass"));
        assertThrows(BusinessException.class, () -> userManagementService.resetPasswordFromUsername("invalid", "newPass"));
    }

    @Test
    void testGetUserByUsername() throws BusinessException {
        userManagementService.createUser("get.user", "pass", true, new String[]{"CLIENT"});
        assertDoesNotThrow(() -> userManagementService.getUserByUsername("get.user"));
        assertThrows(BusinessException.class, () -> userManagementService.getUserByUsername("invalid"));
    }

    @Test
    void testUpdateUser() throws BusinessException {
        userManagementService.createUser("update.user", "pass", true, new String[]{"READER"});
        assertDoesNotThrow(() -> userManagementService.updateUser("update.user", new String[]{"ADMIN"}));
        assertThrows(BusinessException.class, () -> userManagementService.updateUser("invalid", new String[]{"ADMIN"}));
    }

    @Test
    void testChangePassword() throws BusinessException {
        userManagementService.createUser("change.user", "oldPass", true, new String[]{"CLIENT"});
        assertDoesNotThrow(() -> userManagementService.changePasswordFromUsername("change.user", "newPass", "oldPass"));
        assertThrows(BusinessException.class, () -> userManagementService.changePasswordFromUsername("change.user", "newPass", "wrongPass"));
    }

    @Test
    void testDeleteUser() throws BusinessException {
        userManagementService.createUser("del.user", "pass", true, new String[]{"CLIENT"});
        assertDoesNotThrow(() -> userManagementService.deleteUser("del.user"));
        assertThrows(BusinessException.class, () -> userManagementService.deleteUser("del.user"));
    }

    @Test
    void testEnableDisableUser() throws BusinessException {
        userManagementService.createUser("toggle.user", "pass", true, new String[]{"CLIENT"});
        assertDoesNotThrow(() -> userManagementService.disableUser("toggle.user"));
        assertDoesNotThrow(() -> userManagementService.enableUser("toggle.user"));
        assertThrows(BusinessException.class, () -> userManagementService.disableUser("invalid"));
        assertThrows(BusinessException.class, () -> userManagementService.enableUser("invalid"));
    }
}
