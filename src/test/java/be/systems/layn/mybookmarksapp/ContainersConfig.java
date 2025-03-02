package be.systems.layn.mybookmarksapp;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration(proxyBeanMethods = false)
public class ContainersConfig {

	static final String MONGODB_IMAGE = "mongo:8.0.5";
	static final String POSTGRES_IMAGE = "postgres:15-alpine";

	@Bean
	@RestartScope
	@ServiceConnection
	MongoDBContainer mongoDBContainer() {
		return new MongoDBContainer(MONGODB_IMAGE)
				.withAccessToHost(true)
				;
	}

//	@Bean
//	@RestartScope
//	@ServiceConnection
//	PostgreSQLContainer<?> postgreSQLContainer() {
//		return new PostgreSQLContainer<>(POSTGRES_IMAGE)
//				.withAccessToHost(true);
//	}
}
