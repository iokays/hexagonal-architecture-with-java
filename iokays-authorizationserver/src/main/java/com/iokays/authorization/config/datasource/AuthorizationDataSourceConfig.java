package com.iokays.authorization.config.datasource;

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
public class AuthorizationDataSourceConfig {

    @Primary
    @Bean(name = "authorizationDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.authorization")
    public DataSource dataSource() {
        log.info("Creating authorizationDataSource");
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "authorizationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("authorizationDataSource") DataSource dataSource) {
        log.info("Creating authorizationEntityManagerFactory");
        final var result = builder
                .dataSource(dataSource)
                .packages("com.iokays.authorization.core.domain", "com.iokays.common.domain")
                .persistenceUnit("authorization")
                .build();
        log.info("Created authorizationEntityManagerFactory");
        return result;
    }

    @Primary
    @Bean(name = "authorizationTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("authorizationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        log.info("Creating authorizationTransactionManager");
        return new JpaTransactionManager(entityManagerFactory);
    }

}
