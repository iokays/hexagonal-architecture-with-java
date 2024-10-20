package com.iokays.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;
import org.springframework.integration.jdbc.store.channel.H2ChannelMessageStoreQueryProvider;

import javax.sql.DataSource;

/**
 * Spring Integration，SpringEvent配置
 */
@Configuration
@EnableIntegration
public class MyDomainEventStoreIntegrationConfiguration {

    @Bean
    public JdbcChannelMessageStore domainEventMessageStore(final DataSource dataSource) {
        final var result = new JdbcChannelMessageStore(dataSource);

        //提供默认的SQL
        result.setChannelMessageStoreQueryProvider(new H2ChannelMessageStoreQueryProvider());

        //将Spring Integration 的Message默认序列化成JSON序列化方式
        result.setSerializer(DefaultJacksonMessageMapper::toJson);
        result.setDeserializer(DefaultJacksonMessageMapper::fromJsonStream);

        result.setPriorityEnabled(true);
        return result;
    }

}

