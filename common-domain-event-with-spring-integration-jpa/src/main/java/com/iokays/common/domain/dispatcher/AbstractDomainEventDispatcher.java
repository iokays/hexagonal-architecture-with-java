package com.iokays.common.domain.dispatcher;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.domain.localmessage.AbstractLocalMessage;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.integration.dsl.ConsumerEndpointSpec;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.integration.jpa.dsl.Jpa;
import org.springframework.integration.jpa.support.PersistMode;
import org.springframework.messaging.support.GenericMessage;

import java.util.function.Function;

/**
 * DomainEventDispatcher
 */
public abstract class AbstractDomainEventDispatcher {

    private final EntityManagerFactory entityManagerFactory;

    protected AbstractDomainEventDispatcher(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * 将DomainEvent事件 存储在: LocalMessage,或指定的表。
     *
     * @return
     */
    protected final IntegrationFlow domainEventFlow(Function<GenericMessage<?>, AbstractLocalMessage<?>> messageBuilder) {
        //将 ApplicationEvent 转换为 message
        final var eventsAdapter = new ApplicationEventListeningMessageProducer();
        eventsAdapter.setEventTypes(DomainEvent.class);

        return IntegrationFlow
                .from(eventsAdapter)
                .transform(GenericMessage.class, messageBuilder::apply)
                .handle(Jpa.outboundAdapter(this.entityManagerFactory).persistMode(PersistMode.PERSIST), ConsumerEndpointSpec::transactional)
                .get();
    }


}
