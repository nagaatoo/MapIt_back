package ru.numbDev.mapitresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.numbDev.mapitresource.repository.postgres")
@EnableMongoRepositories("ru.numbDev.mapitresource.repository.mongo")
public class MapitResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapitResourceApplication.class, args);
	}

}
