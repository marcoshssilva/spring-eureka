package br.com.marcoshssilva.springbooteureka.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;

@lombok.extern.slf4j.Slf4j
@EnableEurekaServer
@Configuration
public class EurekaServerConfiguration {

    @Value("${eureka.datacenter}")
    private String eurekaDatacenter;

    @Value("${eureka.environment}")
    private String eurekaEnvironment;

    @PostConstruct()
    public void initPostConstruct() {
        log.info("Eureka server configured with [DATACENTER={}] [ENVIRONMENT={}] - If you need to change, please set env for eureka.datacenter and eureka.environment", eurekaDatacenter, eurekaEnvironment);
    }
}
