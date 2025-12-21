package br.com.marcoshssilva.springbooteureka.domain.tasks;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@lombok.RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
@Component
public class InitMetricsUserIfNotExistsTask {
    private static final String DEFAULT_ROLE = UserRoles.METRICS.getAuthority();

    @Value("${eureka.instance.metadata-map.user.name}")
    String metricsUsername = "metrics";

    @Value("${eureka.instance.metadata-map.user.password}")
    String metricsPassword = "changeit";

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Async
    public CompletableFuture<Void> executeTask() {
        log.info("Checking if user {} exists in database.", metricsUsername);
        if (!userDetailsManager.userExists(metricsUsername)) {
            log.info("User {} not found in database. Creating...", metricsUsername);
            userDetailsManager.createUser(User.builder().username(metricsUsername).password(passwordEncoder.encode(metricsPassword)).roles(DEFAULT_ROLE).build());
            log.info("User {} created succesfully.", metricsUsername);
        } else {
            log.info("Skip create user {}. Already exists in database.", metricsUsername);
        }
        return CompletableFuture.completedFuture(null);
    }
}
