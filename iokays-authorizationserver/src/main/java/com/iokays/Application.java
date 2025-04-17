package com.iokays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
//@EnableCaching
@EnableIntegration
@EnableJpaAuditing
@EnableJpaRepositories(
        entityManagerFactoryRef = "authorizationEntityManagerFactory", // 指定自定义的 EntityManagerFactory
        transactionManagerRef = "authorizationTransactionManager"     // 指定自定义的 TransactionManager
)
@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy(exposeProxy = true) // 通过 AopContext.currentProxy() 获取当前代理对象
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
