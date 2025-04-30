
package com.iokays.ai.config.datasource;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class AiDataSourceConfig {

    @Primary
    @Bean(name = "aiDataSource")
    @ConfigurationProperties(prefix = "spring.jpa.datasource")
    public DataSource dataSource() {
        log.info("Creating aiDataSource");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "aiEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("aiDataSource") DataSource dataSource) {
        log.info("Creating aiEntityManagerFactory");
        return builder
                .dataSource(dataSource)
                .packages("com.iokays.ai.core.domain", "com.iokays.common.domain")
                .persistenceUnit("ai")
                .build();
    }

    @Primary
    @Bean(name = "aiTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("aiEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        log.info("Creating aiTransactionManager");
        return new JpaTransactionManager(entityManagerFactory);
    }

}