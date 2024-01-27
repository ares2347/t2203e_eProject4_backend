package com.eproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.eproject.data")
@OpenAPIDefinition
public class T2203eEProject4BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(T2203eEProject4BackendApplication.class, args);
	}

}
