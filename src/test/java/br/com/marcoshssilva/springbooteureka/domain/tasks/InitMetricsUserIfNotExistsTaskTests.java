package br.com.marcoshssilva.springbooteureka.domain.tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InitMetricsUserIfNotExistsTaskTests {

    @Test
    void testExecuteTaskUserExists() {
        UserDetailsManager userDetailsManager = Mockito.mock(UserDetailsManager.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        InitMetricsUserIfNotExistsTask task = new InitMetricsUserIfNotExistsTask(userDetailsManager, passwordEncoder);

        when(userDetailsManager.userExists(any())).thenReturn(true);

        CompletableFuture<Void> future = task.executeTask();
        future.join();

        verify(userDetailsManager, times(0)).createUser(any());
    }

    @Test
    void testExecuteTaskUserNotExists() {
        UserDetailsManager userDetailsManager = Mockito.mock(UserDetailsManager.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        InitMetricsUserIfNotExistsTask task = new InitMetricsUserIfNotExistsTask(userDetailsManager, passwordEncoder);

        when(userDetailsManager.userExists(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("hash");

        CompletableFuture<Void> future = task.executeTask();
        future.join();

        verify(userDetailsManager, times(1)).createUser(any());
    }
}
