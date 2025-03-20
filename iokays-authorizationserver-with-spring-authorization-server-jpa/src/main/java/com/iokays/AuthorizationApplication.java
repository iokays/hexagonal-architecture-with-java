package com.iokays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableCaching
@EnableIntegration
@EnableJpaAuditing
@EnableJpaRepositories(
        entityManagerFactoryRef = "authorizationEntityManagerFactory", // 指定自定义的 EntityManagerFactory
        transactionManagerRef = "authorizationTransactionManager"     // 指定自定义的 TransactionManager
)
@SpringBootApplication
@EnableTransactionManagement
public class AuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
