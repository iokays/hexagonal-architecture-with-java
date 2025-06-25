package com.iokays.dispatch.config.dispatch;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.channel.PostgresChannelMessageTableSubscriber;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;
import org.springframework.integration.jdbc.store.channel.PostgresChannelMessageStoreQueryProvider;

import javax.sql.DataSource;
import java.sql.DriverManager;

/**
 * DomainEventSecondDispatcher
 * 将LocalMessage 发送到 MQ.
 */
@Slf4j
@Configuration
public class DomainEventSecondDispatcher {


    @Bean
    public JdbcChannelMessageStore messageStore(@Qualifier("localMessageDataSource") DataSource dataSource) {
        final var messageStore = new JdbcChannelMessageStore(dataSource);
        messageStore.setTablePrefix("T_");
        messageStore.setChannelMessageStoreQueryProvider(new PostgresChannelMessageStoreQueryProvider());
        return messageStore;
    }

    @Bean
    public PostgresChannelMessageTableSubscriber subscriber() {
        return new PostgresChannelMessageTableSubscriber(() ->
                DriverManager.getConnection(
                                "jdbc:postgresql://119.91.136.31:5432/iokays?currentSchema=authorization",
                                "iokaysuser",
                                "3e4a5d6e7f8a9b0c1d2e3f4a5b6c7d8e"
                        )
                        .unwrap(PgConnection.class)
        );
    }

    @Bean
    public PostgresSubscribableChannel channel(JdbcChannelMessageStore messageStore, PostgresChannelMessageTableSubscriber subscriber) {
        return new PostgresSubscribableChannel(messageStore, "some group", subscriber);
    }
}
