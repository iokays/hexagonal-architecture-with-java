package com.iokays.webflux.config.datasource;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;


@Slf4j
@Configuration
public class SessionDataSourceConfig {

    @Bean("sessionConnectionFactory")
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .tcp("localhost", 9092, "~/h2/db-iokays-session")
                .build()
        );
    }

    @Bean("sessionEntityTemplate")
    public R2dbcEntityTemplate r2dbcEntityTemplate() {
        return new R2dbcEntityTemplate(this.connectionFactory());
    }
}
