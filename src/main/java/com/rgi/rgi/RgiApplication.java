package com.rgi.rgi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class RgiApplication {

	private static final Logger log = LoggerFactory.getLogger(RgiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RgiApplication.class, args);

		log.info("Application Task list started...");

	}
}