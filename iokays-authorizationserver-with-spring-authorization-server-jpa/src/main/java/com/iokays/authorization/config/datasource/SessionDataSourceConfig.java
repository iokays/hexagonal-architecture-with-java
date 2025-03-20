package com.iokays.authorization.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.session.jdbc.config.annotation.SpringSessionDataSource;
import org.springframework.session.jdbc.config.annotation.SpringSessionTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class SessionDataSourceConfig {

    @SpringSessionDataSource
    @Bean(name = "sessionDataSource")
    @ConfigurationProperties(prefix = "spring.session.datasource")
    public DataSource dataSource() {
        log.info("Creating sessionDataSource");
        return DataSourceBuilder.create().build();
    }

    @Bean("sessionTransactionManager")
    @SpringSessionTransactionManager
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
