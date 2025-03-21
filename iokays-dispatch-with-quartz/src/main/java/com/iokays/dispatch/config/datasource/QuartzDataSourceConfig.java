package com.iokays.dispatch.config.datasource;

import com.iokays.dispatch.config.quartz.AutowiringSpringBeanJobFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class QuartzDataSourceConfig {

    @Primary
    @QuartzDataSource
    @Bean("quartzDataSource")
    @ConfigurationProperties(prefix = "spring.quartz.datasource")
    public DataSource dataSource() {
        log.info("Creating quartzDataSource");
        return DataSourceBuilder.create().build();
    }


    @Primary
    @QuartzTransactionManager
    @Bean("quartzTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("quartzDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}