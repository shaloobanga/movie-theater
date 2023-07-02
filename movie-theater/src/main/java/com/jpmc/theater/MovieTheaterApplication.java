package com.jpmc.theater;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * MovieTheaterApplication is the main class of this application.
 * It is annotated with @SpringBootApplication which encapsulates @SpringBootConfiguration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
  */
@SpringBootApplication
@ComponentScan(basePackages = "com.jpmc.theater")
public class MovieTheaterApplication {
	
	private static Logger logger = LoggerFactory.getLogger(MovieTheaterApplication.class);
	
	public static void main(String[] args) {
		logger.info("Starting Movie Theater application");
		SpringApplication.run(MovieTheaterApplication.class, args);
	}

}
