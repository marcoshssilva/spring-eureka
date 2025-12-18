package br.com.marcoshssilva.springbooteureka.domain.tasks;

import br.com.marcoshssilva.springbooteureka.controller.data.etc.UserRoles;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@lombok.RequiredArgsConstructor
@lombok.extern.slf4j.Slf4j
@Component
public class InitSuperUserIfNotExistsTask {
    private static final String DEFAULT_HASH_PASSWORD = "$100801$9aSNEKS2azINKKadHUU5sw==$yA18DF/L2PnKPplJhMLm5Z7JhAT2TAQPq0PipdxjDxU=";
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_ROLE = UserRoles.ADMIN.getAuthority();


    private final UserDetailsManager userDetailsManager;

    @Async
    public CompletableFuture<Void> executeTask() {
        log.info("Checking if default admin exists in database.");
        if (!userDetailsManager.userExists(DEFAULT_USERNAME)) {
            log.info("User {} not found in database. Creating...", DEFAULT_USERNAME);
            userDetailsManager.createUser(User.builder().username(DEFAULT_USERNAME).password(DEFAULT_HASH_PASSWORD).roles(DEFAULT_ROLE).build());
            log.info("User {} created succesfully.", DEFAULT_USERNAME);
        } else {
            log.info("Skip create default admin. Already exists in database.");
        }
        return CompletableFuture.completedFuture(null);
    }
}
