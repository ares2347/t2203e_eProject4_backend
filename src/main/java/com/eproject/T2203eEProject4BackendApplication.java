package com.eproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.eproject.data")
@OpenAPIDefinition
@EnableScheduling
public class T2203eEProject4BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(T2203eEProject4BackendApplication.class, args);
	}

}
