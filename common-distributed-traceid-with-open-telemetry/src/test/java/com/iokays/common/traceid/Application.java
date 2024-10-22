package com.iokays.common.traceid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        final var app = new SpringApplication(Application.class);
        // app.setBannerMode(Banner.Mode.OFF); 是否显示 Spring Boot 的 Banner
        app.run(args);
    }

}
