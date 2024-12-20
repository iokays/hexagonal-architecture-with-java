package com.iokays.core.adapter.messaging.dispatcher;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.domain.localmessage.LocalMessage;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.support.GenericMessage;

/**
 * DomainEventDispatcher
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DomainEventDispatcher {

    @Resource
    private final EntityManagerFactory entityManagerFactory;

    /**
     * 将DomainEvent事件 存储在: LocalMessage
     * @return
     */
    @Bean
    public IntegrationFlow domainEventFlow() {
        final var eventsAdapter = new ApplicationEventListeningMessageProducer();
        eventsAdapter.setEventTypes(DomainEvent.class);

        return IntegrationFlow
                .from(eventsAdapter)
                .transform(GenericMessage.class, LocalMessage::new)
                .handle(Jpa.outboundAdapter(this.entityManagerFactory)
                                .entityClass(LocalMessage.class)
                                .persistMode(PersistMode.PERSIST),
                        ConsumerEndpointSpec::transactional
                ).get();
    }

}
