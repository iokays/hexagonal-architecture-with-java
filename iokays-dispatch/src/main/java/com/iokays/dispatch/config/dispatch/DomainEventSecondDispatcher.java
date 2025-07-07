package com.iokays.dispatch.config.dispatch;

import com.iokays.common.integration.jdbc.store.channel.DomainEventInteropDeserializer;
import com.iokays.common.integration.jdbc.store.channel.MyPostgresChannelMessageStoreQueryProvider;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jdbc.channel.PostgresChannelMessageTableSubscriber;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;

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
        messageStore.setTablePrefix("int_");
        messageStore.setChannelMessageStoreQueryProvider(new MyPostgresChannelMessageStoreQueryProvider());
        messageStore.setDeserializer(new DomainEventInteropDeserializer()); //因为本模块是一个单独的模块,所以需要使用 DomainEventInteropDeserializer.
        return messageStore;
    }

    @Bean
    public PostgresChannelMessageTableSubscriber subscriber(
            @Value("${spring.datasource.localmessage.jdbc-url}") String url,
            @Value("${spring.datasource.localmessage.username}") String username,
            @Value("${spring.datasource.localmessage.password}") String password) {
        return new PostgresChannelMessageTableSubscriber(() ->
                DriverManager.getConnection(url, username, password).unwrap(PgConnection.class)
        );
    }

    @Bean
    public PostgresSubscribableChannel channel(JdbcChannelMessageStore messageStore, PostgresChannelMessageTableSubscriber subscriber) {
        return new PostgresSubscribableChannel(messageStore, "authorization", subscriber);
    }
}
