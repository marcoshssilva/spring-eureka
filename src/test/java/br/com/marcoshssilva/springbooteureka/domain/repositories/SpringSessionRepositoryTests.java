package br.com.marcoshssilva.springbooteureka.domain.repositories;

import br.com.marcoshssilva.springbooteureka.domain.entities.SpringSession;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SpringSessionRepositoryTests {

    @Autowired
    private SpringSessionRepository springSessionRepository;

    @MockBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testSaveAndFind() {
        SpringSession session = new SpringSession("p1", "s1", 100L, 200L, 1800, 300L, "admin");
        springSessionRepository.save(session);

        Optional<SpringSession> found = springSessionRepository.findById("p1");
        assertTrue(found.isPresent());
        assertEquals("s1", found.get().getSessionId());
    }
}
