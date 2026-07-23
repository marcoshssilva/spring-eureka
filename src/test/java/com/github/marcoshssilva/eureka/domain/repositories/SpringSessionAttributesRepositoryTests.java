package com.github.marcoshssilva.eureka.domain.repositories;

import com.github.marcoshssilva.eureka.domain.entities.SpringSession;
import com.github.marcoshssilva.eureka.domain.entities.SpringSessionAttributes;
import com.github.marcoshssilva.eureka.domain.entities.SpringSessionAttributesPK;
import com.github.marcoshssilva.eureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import com.github.marcoshssilva.eureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class SpringSessionAttributesRepositoryTests {

    @Autowired
    private SpringSessionAttributesRepository springSessionAttributesRepository;

    @Autowired
    private SpringSessionRepository springSessionRepository;

    @MockitoBean
    private InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;

    @MockitoBean
    private InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

    @Test
    void testSaveAndFind() {
        SpringSession session = new SpringSession("p1", "s1", 100L, 200L, 1800, 300L, "admin");
        springSessionRepository.save(session);

        SpringSessionAttributesPK pk = new SpringSessionAttributesPK("p1", "attr1");
        SpringSessionAttributes attr = new SpringSessionAttributes(pk, session, new byte[]{1, 2, 3});
        springSessionAttributesRepository.save(attr);

        Optional<SpringSessionAttributes> found = springSessionAttributesRepository.findById(pk);
        assertTrue(found.isPresent());
        assertArrayEquals(new byte[]{1, 2, 3}, found.get().getAttributeBytes());
    }
}
