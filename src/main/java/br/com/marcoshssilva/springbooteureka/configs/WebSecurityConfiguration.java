package br.com.marcoshssilva.springbooteureka.configs;

import br.com.marcoshssilva.springbooteureka.domain.services.UserManagementService;
import br.com.marcoshssilva.springbooteureka.domain.services.impl.CustomUserDetailsManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableMethodSecurity()
@EnableWebSecurity
public class WebSecurityConfiguration {
    static final String ROLE_METRICS = "METRICS";
    static final String ROLE_ADMIN   = "ADMIN";
    static final String ROLE_READER  = "READER";
    static final String ROLE_CLIENT  = "CLIENT";

    static final String[] ALLOW_BY_ROLE_METRICS = { "/actuator/**", "/actuator" };
    static final String[] ALLOW_BY_ROLE_CLIENT = { "/eureka/v2/apps", "/eureka/v2/apps/**" };
    static final String[] ALLOW_BY_ROLE_ADMIN  = { "/api/admin/**", "/h2-console/**" };
    static final String[] PUBLIC_ROUTES = { "/", "/lastn", "/favicon.ico", "/eureka/css/**", "/eureka/js/**", "/eureka/fonts/**", "/eureka/images/**", "/actuator/health", "/actuator/health/liveness", "/actuator/health/readiness" };

    @Primary
    @Bean
    public UserDetailsManager defaultUserDetailsManagerBean(DataSource dataSource, UserManagementService userManagementService) {
        return new CustomUserDetailsManagerImpl(dataSource, userManagementService);
    }

    @Primary
    @Bean
    public PasswordEncoder defaultPasswordEncoder() {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain securityFilterChainConfigure(HttpSecurity http) {
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(
                httpRequestsConfigurer -> httpRequestsConfigurer
                        .requestMatchers(ALLOW_BY_ROLE_ADMIN).hasAnyRole(ROLE_ADMIN)
                        .requestMatchers(ALLOW_BY_ROLE_CLIENT).hasAnyRole(ROLE_CLIENT, ROLE_ADMIN)
                        .requestMatchers(ALLOW_BY_ROLE_METRICS).hasAnyRole(ROLE_METRICS, ROLE_ADMIN)
                        // ONLY GET METHOD allowed to ROLE_READER
                        .requestMatchers(HttpMethod.GET, ALLOW_BY_ROLE_CLIENT).hasAnyRole(ROLE_READER, ROLE_ADMIN)
                        // ONLY GET METHOD allowed to ANYONE
                        .requestMatchers(HttpMethod.GET, PUBLIC_ROUTES).permitAll()
                        .anyRequest().authenticated()
        );
        return http.build();
    }

}
