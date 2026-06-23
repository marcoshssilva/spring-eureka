package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.Role;
import br.com.marcoshssilva.springbooteureka.domain.entities.RolePK;
import br.com.marcoshssilva.springbooteureka.domain.entities.User;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testSaveAndFindAllByUsername() {
        User user = new User("tester", "pass", true, null);
        userRepository.save(user);

        Role role = new Role(new RolePK(user, "ROLE_ADMIN"));
        roleRepository.save(role);

        Set<Role> roles = roleRepository.findAllByUsername("tester");
        assertEquals(1, roles.size());
        assertTrue(roles.stream().anyMatch(r -> r.getId().getAuthority().equals("ROLE_ADMIN")));
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
