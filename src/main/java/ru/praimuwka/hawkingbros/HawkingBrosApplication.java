package ru.praimuwka.hawkingbros;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Hawking bros adapter REST API", version = "1.0",
                                description = "<h3>Добро пожаловать! Поиграемся с апишками ? \uD83D\uDE09</h3>"))
public class HawkingBrosApplication {
	public static void main(String[] args) {
		SpringApplication.run(HawkingBrosApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}
}
