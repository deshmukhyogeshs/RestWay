package com.myrest.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.myrest")
public class RestWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWayApplication.class, args);
	}

}

