package br.com.marcoshssilva.springbooteureka;

import br.com.marcoshssilva.springbooteureka.domain.tasks.InitMetricsUserIfNotExistsTask;
import br.com.marcoshssilva.springbooteureka.domain.tasks.InitSuperUserIfNotExistsTask;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@lombok.RequiredArgsConstructor
@EnableEurekaServer
@SpringBootApplication
public class SpringBootEurekaApplication implements CommandLineRunner {
	private final InitSuperUserIfNotExistsTask initSuperUserIfNotExistsTask;
	private final InitMetricsUserIfNotExistsTask initMetricsUserIfNotExistsTask;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEurekaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initSuperUserIfNotExistsTask.executeTask();
		initMetricsUserIfNotExistsTask.executeTask();
	}
}
