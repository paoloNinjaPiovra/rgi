package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class RgiApplication extends Application {

	private static final Logger log = LoggerFactory.getLogger(RgiApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(RgiApplication.class, args);

		log.info("Application Task list started...");
	}

	@Bean
	public CommandLineRunner demo (UserRepository userRepository, TaskRepository taskRepository) {
		return (args) -> {
			setUp(userRepository, taskRepository);
		};
	}
}