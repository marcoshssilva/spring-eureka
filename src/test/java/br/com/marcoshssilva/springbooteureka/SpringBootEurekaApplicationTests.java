package br.com.marcoshssilva.springbooteureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@lombok.extern.slf4j.Slf4j
@SpringBootTest
class SpringBootEurekaApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> {log.info("Application startup with SUCCESS"); });
	}

}
