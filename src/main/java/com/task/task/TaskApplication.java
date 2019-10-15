package com.task.task;

import com.task.task.repository.TaskRepository;
import com.task.task.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
public class TaskApplication extends Application {

    private static final Logger log = LoggerFactory.getLogger(TaskApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(TaskApplication.class, args);

        log.info("Application task list started...");
    }

    @Bean
    public CommandLineRunner tasks(UserRepository userRepository, TaskRepository taskRepository) {
        return (args) -> {
            setUp(userRepository, taskRepository);
        };
    }
}