package com.iokays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
@EnableTransactionManagement
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @ConfigurationProperties(prefix = "spring.datasource.message")
    @Bean(name = "messageDataSource")
    public DataSource dataSource() {
        log.info("Creating messageDataSource");
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.qrtz")
    @Bean(name = "qrtzDataSource")
    public DataSource qrtz() {
        log.info("Creating qrtzDataSource");
        return DataSourceBuilder.create().build();
    }

}
