package com.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.data")
public class T2203eEProject4BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(T2203eEProject4BackendApplication.class, args);
	}

}
