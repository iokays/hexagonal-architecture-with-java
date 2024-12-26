package com.iokays.core.adapter.messaging.dispatcher;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.domain.localmessage.LocalMessage;
import com.iokays.core.domain.user.UserLocalMessage;
import com.iokays.core.domain.user.event.UserDomainEvent;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * 将DomainEvent事件 存储在: LocalMessage,或指定的表。
     *
     * @return
     */
    @Bean
    public IntegrationFlow domainEventFlow() {
        //将 ApplicationEvent 转换为 message
        final var eventsAdapter = new ApplicationEventListeningMessageProducer();
        eventsAdapter.setEventTypes(DomainEvent.class);

        return IntegrationFlow
                .from(eventsAdapter)
                .transform(GenericMessage.class, v -> {

                    //指定的
                    if (v.getPayload() instanceof UserDomainEvent<?>) {
                        return new UserLocalMessage(v);
                    }

                    //默认的
                    return new LocalMessage(v);
                })
                .handle(Jpa.outboundAdapter(this.entityManagerFactory).persistMode(PersistMode.PERSIST), ConsumerEndpointSpec::transactional)
                .get();
    }

}
