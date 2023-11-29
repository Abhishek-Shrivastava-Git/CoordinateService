package com.find.coordinate.FindCoordinate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FindCoordinateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindCoordinateApplication.class, args);
	}

	@Bean
	RestTemplate getBean(){
		return new RestTemplate();
	}

}
