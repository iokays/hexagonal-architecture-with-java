package com.iokays.ai.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class AiDataSourceConfig {

    @Primary
    @Bean(name = "aiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ai")
    public DataSource dataSource() {
        log.info("Creating aiDataSource");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "aiJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("aiDataSource") DataSource dataSource) {
        log.info("Creating aiJdbcTemplate");
        return new JdbcTemplate(dataSource);
    }


}