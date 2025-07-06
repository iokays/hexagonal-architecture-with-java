package com.iokays.common;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.integration.jdbc.store.channel.DomainEventInteropDeserializer;
import com.iokays.common.integration.jdbc.store.channel.DomainEventSerializer;
import com.iokays.common.integration.jdbc.store.channel.MyPostgresChannelMessageStoreQueryProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jdbc.channel.PostgresChannelMessageTableSubscriber;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;

import javax.sql.DataSource;
import java.sql.DriverManager;

@EnableIntegration
@SpringBootApplication
public class ApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

    @Bean
    public JdbcChannelMessageStore messageStore(DataSource dataSource) {
        final var messageStore = new JdbcChannelMessageStore(dataSource);
        messageStore.setChannelMessageStoreQueryProvider(new MyPostgresChannelMessageStoreQueryProvider());
        messageStore.setSerializer(new DomainEventSerializer());
        messageStore.setDeserializer(new DomainEventInteropDeserializer());
        return messageStore;
    }

    @Bean
    public QueueChannel queueChannel(JdbcChannelMessageStore messageStore) {
        return new PriorityChannel(messageStore, "authorization");
    }

    @Bean
    public ApplicationEventListeningMessageProducer eventsAdapter(QueueChannel queueChannel) {
        final var producer = new ApplicationEventListeningMessageProducer();
        producer.setEventTypes(DomainEvent.class);
        producer.setOutputChannel(queueChannel);
        return producer;
    }

    @Bean
    public PostgresChannelMessageTableSubscriber subscriber(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        return new PostgresChannelMessageTableSubscriber(() ->
                DriverManager.getConnection(url, username, password).unwrap(org.postgresql.jdbc.PgConnection.class)
        );
    }

    @Bean
    public PostgresSubscribableChannel postgresSubscribableChannel(
            PostgresChannelMessageTableSubscriber subscriber,
            JdbcChannelMessageStore messageStore) {
        return new PostgresSubscribableChannel(messageStore, "authorization", subscriber);
    }

}
