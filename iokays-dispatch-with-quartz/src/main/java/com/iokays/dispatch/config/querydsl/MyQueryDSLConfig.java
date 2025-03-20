package com.iokays.dispatch.config.querydsl;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
public class MyQueryDSLConfig {

    @Bean
    public SQLTemplates sqlTemplates() {
        return new H2Templates();
    }

    @Bean
    public SQLQueryFactory sqlQueryFactory(
            @Qualifier("quartzDataSource") final DataSource dataSource,
            @Qualifier("quartzTransactionManager") final TransactionManager transactionManager,
            final SQLTemplates sqlTemplates
    ) {
        final var configuration = new com.querydsl.sql.Configuration(sqlTemplates);
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }

}
