package com.iokays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAsync
@SpringBootApplication
@EnableR2dbcRepositories
@RestController
public class WebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @GetMapping
    public String hello() {
        return "Hello, Webflux!";
    }

    @GetMapping("/")
    public String hello2() {
        return "Hello, Webflux!";
    }

}
