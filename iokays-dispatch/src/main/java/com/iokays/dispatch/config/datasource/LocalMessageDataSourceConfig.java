package com.iokays.dispatch.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class LocalMessageDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.localmessage")
    @Bean(name = "localMessageDataSource")
    public DataSource dataSource() {
        log.info("Creating localMessageDataSource");
        return DataSourceBuilder.create().build();
    }


    @Bean("localMessageTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("localMessageDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
