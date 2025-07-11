package com.iokays.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class ApplicationTest {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(ApplicationTest.class, args);
    }
}
