package ru.praimuwka.konustest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// @EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories("ru.praimuwka.konustest.etl.repositories")
public class KonusApp {
	public static void main(String[] args) {
		SpringApplication.run(KonusApp.class, args);
	}

	// @Bean
	// public Docket api() {
	// 	return new Docket(DocumentationType.SWAGGER_2)
	// 		.select()
	// 		.apis(RequestHandlerSelectors.basePackage("ru.praimuwka.konustest.rest.controllers"))
	// 		.paths(PathSelectors.any())
	// 		.build()
	// 		.apiInfo(apiInfo());
	// }
	//
	// private ApiInfo apiInfo() {
	// 	return new ApiInfoBuilder()
	// 		.title("Library API")
	// 		.description("Konus Test API")
	// 		.version("1.0")
	// 		.contact(new Contact("Kir Bush", "https://github.com/praimuwka", "kbuschuev@gmail.com"))
	// 		.build();
	// }

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		return objectMapper;
	}
}
