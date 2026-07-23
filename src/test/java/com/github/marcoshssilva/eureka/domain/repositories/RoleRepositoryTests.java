package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.Role;
import com.github.marcoshssilva.eureka.domain.entities.RolePK;
import com.github.marcoshssilva.eureka.domain.entities.User;
import com.github.marcoshssilva.eureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import com.github.marcoshssilva.eureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @MockitoBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockitoBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testSaveAndFindAllByUsername() {
        User user = new User("tester", "pass", true, null);
        userRepository.save(user);

        Role role = new Role(new RolePK(user, "ROLE_ADMIN"));
        roleRepository.save(role);

        Set<Role> roles = roleRepository.findAllByUsername("tester");
        assertEquals(1, roles.size());
        assertTrue(roles.stream().anyMatch(r -> r.getId().authority().equals("ROLE_ADMIN")));
    }

    @Test
    void testDeleteAllByUsername() {
        User user = new User("tester2", "pass", true, null);
        userRepository.save(user);

        roleRepository.save(new Role(new RolePK(user, "ROLE_ADMIN")));
        roleRepository.save(new Role(new RolePK(user, "ROLE_USER")));

        assertEquals(2, roleRepository.findAllByUsername("tester2").size());

        roleRepository.deleteAllById_User_Username("tester2");
        assertEquals(0, roleRepository.findAllByUsername("tester2").size());
    }
}
