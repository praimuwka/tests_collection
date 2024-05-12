package ru.praimuwka.konustest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.praimuwka.konustest.etl.repositories")
public class KonustApp {
	public static void main(String[] args) {
		SpringApplication.run(KonustApp.class, args);
	}
}
