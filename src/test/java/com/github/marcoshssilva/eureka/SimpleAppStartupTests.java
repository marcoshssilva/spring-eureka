package com.github.marcoshssilva.eureka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SimpleAppStartupTests {
    @DisplayName("Must execute app using initial setup as profile DEFAULT")
    @Test
    void runAppWithDefaultConfigs() {
        assertDoesNotThrow(() -> {
            SpringBootEurekaApplication.main(new String[] {"--server.port=0", "--management.server.port=0"});
        });
    }
}
