package com.iokays.config.querydsl;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringConnectionProvider;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyQueryDSLConfig {

    @Bean
    public SQLTemplates sqlTemplates() {
        return new H2Templates();
    }

    @Bean
    public SQLQueryFactory sqlQueryFactory(final DataSource dataSource, final SQLTemplates sqlTemplates) {
        final var configuration = new com.querydsl.sql.Configuration(sqlTemplates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));
    }

}
