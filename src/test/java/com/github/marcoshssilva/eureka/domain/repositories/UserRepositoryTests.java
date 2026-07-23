package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.User;
import com.github.marcoshssilva.eureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import com.github.marcoshssilva.eureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @MockitoBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockitoBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testSaveAndGetById() {
        User user = new User("tester", "password", true, null);
        userRepository.save(user);

        Optional<User> found = userRepository.findById("tester");
        assertTrue(found.isPresent());
        assertEquals("tester", found.get().getUsername());
    }

    @Test
    void testFindByUsername() {
        User user = new User("tester2", "password", true, null);
        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("tester2");
        assertTrue(found.isPresent());
        assertEquals("tester2", found.get().getUsername());
    }

    @Test
    void testPagination() {
        for (int i = 0; i < 10; i++) {
            userRepository.save(new User("user" + i, "pass", true, null));
        }

        Page<User> page = userRepository.findAll(PageRequest.of(0, 5));
        assertEquals(5, page.getContent().size());
        assertTrue(page.getTotalElements() >= 10);
    }

    @Test
    void testDelete() {
        User user = new User("deluser", "pass", true, null);
        userRepository.save(user);
        assertTrue(userRepository.findById("deluser").isPresent());

        userRepository.delete(user);
        assertFalse(userRepository.findById("deluser").isPresent());
    }
}
