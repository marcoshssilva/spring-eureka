package br.com.marcoshssilva.springbooteureka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class SpringBootAppStartupTests {
	private static final Logger log = LoggerFactory.getLogger(SpringBootAppStartupTests.class);

	@DisplayName("Must execute app using initial setup as profile TEST")
	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> {log.info("Application startup with SUCCESS"); });
	}

}
