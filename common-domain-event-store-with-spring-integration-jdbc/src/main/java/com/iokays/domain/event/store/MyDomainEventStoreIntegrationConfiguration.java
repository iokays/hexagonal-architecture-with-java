package com.iokays.domain.event.store;

import com.iokays.common.core.event.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.Objects;

/**
 * Spring Integration，SpringEvent配置
 */
@Configuration
@EnableIntegration
public class MyDomainEventStoreIntegrationConfiguration {

    //logger
    private static final Logger log = LoggerFactory.getLogger(MyDomainEventStoreIntegrationConfiguration.class);

    /**
     * 存储领域事件的出站适配器
     *
     * @param dataSource
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "storeDomainEventChannel")
    public MessageHandler jdbcMessageHandler(DataSource dataSource) {
        final var jdbcMessageHandler = new JdbcMessageHandler(dataSource,
                "INSERT INTO LOCAL_MESSAGE (MESSAGE_ID, MESSAGE_BYTES, MESSAGE_STATE, CREATED_DATE, UPDATED_DATE) VALUES (?, ?, 0, ?, ?)");
        jdbcMessageHandler.setPreparedStatementSetter((ps, m) -> {
            if (m instanceof GenericMessage<?> gm) {
                ps.setString(1, Objects.requireNonNull(gm.getHeaders().getId()).toString());
                ps.setBytes(2, DomainEventInputMessageMapper.toBytes(gm.getPayload()));
                final var time = new Date(Objects.requireNonNull(gm.getHeaders().getTimestamp()));
                ps.setDate(3, time);
                ps.setDate(4, time);
            } else {
                throw new IllegalArgumentException("message type not support");
            }
            log.info("message: {}", m);
        });
        return jdbcMessageHandler;
    }

    /**
     * 监听领域事件, 将事件发送到storeDomainEventChannel
     *
     * @return
     */
    @Bean
    public ApplicationEventListeningMessageProducer domainEventMessageProducer() {
        final var result = new ApplicationEventListeningMessageProducer();
        result.setEventTypes(DomainEvent.class);
        result.setOutputChannelName("storeDomainEventChannel");
        return result;
    }

}

