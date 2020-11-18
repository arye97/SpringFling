package com.example.springfling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SpringflingApplication {

	public static void main(String[] args) {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(new ArrayList(Arrays.asList(
				"http://localhost:8080")));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		List<String> exposedHeaders = Arrays.asList("Total-Rows", "Has-Next");
		config.setExposedHeaders(exposedHeaders);
		SpringApplication.run(SpringflingApplication.class, args);
	}

}
