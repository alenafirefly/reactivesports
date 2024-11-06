package com.alenayasko.javamentoring.reactivesports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
public class SportsReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsReactiveApplication.class, args);
	}
}
