package com.example.apipostgress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.apipostgress.models")
public class ApiPostgressApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPostgressApplication.class, args);
	}
}
