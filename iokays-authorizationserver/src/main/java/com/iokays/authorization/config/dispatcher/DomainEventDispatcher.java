package com.iokays.authorization.config.dispatcher;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.integration.jdbc.store.channel.DomainEventDeserializer;
import com.iokays.common.integration.jdbc.store.channel.DomainEventSerializer;
import com.iokays.common.integration.jdbc.store.channel.MyPostgresChannelMessageStoreQueryProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jdbc.store.JdbcChannelMessageStore;

import javax.sql.DataSource;

/**
 * DomainEventDispatcher
 */
@Slf4j
@Configuration
public class DomainEventDispatcher {

    @Bean
    public JdbcChannelMessageStore messageStore(@Qualifier("authorizationDataSource") DataSource dataSource) {
        final var messageStore = new JdbcChannelMessageStore(dataSource);
        messageStore.setChannelMessageStoreQueryProvider(new MyPostgresChannelMessageStoreQueryProvider());
        messageStore.setSerializer(new DomainEventSerializer());
        messageStore.setDeserializer(new DomainEventDeserializer());
        return messageStore;
    }

    @Bean
    public QueueChannel queueChannel(JdbcChannelMessageStore messageStore) {
        return new PriorityChannel(messageStore, "authorization");
    }

    @Bean
    public ApplicationEventListeningMessageProducer messageProducer(QueueChannel queueChannel) {
        final var producer = new ApplicationEventListeningMessageProducer();
        producer.setEventTypes(DomainEvent.class);
        producer.setOutputChannel(queueChannel);
        return producer;
    }


}
