package br.com.marcoshssilva.springbooteureka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@lombok.extern.slf4j.Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SpringBootAppStartupTests {

	@DisplayName("Must execute app using initial setup as profile TEST")
	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> {log.info("Application startup with SUCCESS"); });
	}

}
