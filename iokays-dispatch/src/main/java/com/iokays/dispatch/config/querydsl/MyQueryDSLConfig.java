package com.iokays.dispatch.config.querydsl;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spring.SpringConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyQueryDSLConfig {

    @Bean(name = "quartzSQLQueryFactory")
    public SQLQueryFactory quartzSqlQueryFactory(
            @Qualifier("quartzDataSource") final DataSource dataSource
    ) {
        final var configuration = new com.querydsl.sql.Configuration(new H2Templates());
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }

    @Bean(name = "localMessageSQLQueryFactory")
    public SQLQueryFactory localMessagesqlQueryFactory(
            @Qualifier("localMessageDataSource") final DataSource dataSource
    ) {
        final var configuration = new com.querydsl.sql.Configuration(new H2Templates());
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }

}
