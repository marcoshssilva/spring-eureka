package br.com.marcoshssilva.springbooteureka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SimpleAppStartupTests {
    @DisplayName("Must execute app using initial setup as profile DEFAULT")
    @Test
    void runAppWithDefaultConfigs() {
        assertDoesNotThrow(() -> {
            SpringBootEurekaApplication.main(new String[] {});
        });
    }
}
