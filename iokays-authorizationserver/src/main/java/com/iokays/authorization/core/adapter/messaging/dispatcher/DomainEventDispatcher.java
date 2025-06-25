package com.iokays.authorization.core.adapter.messaging.dispatcher;

import com.iokays.authorization.core.domain.user.UserLocalMessage;
import com.iokays.authorization.core.domain.user.event.UserDomainEvent;
import com.iokays.common.domain.dispatcher.AbstractDomainEventDispatcher;
import com.iokays.common.domain.localmessage.AbstractLocalMessage;
import com.iokays.common.domain.localmessage.LocalMessage;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.support.GenericMessage;

/**
 * DomainEventDispatcher
 */
@Slf4j
@Configuration
public class DomainEventDispatcher extends AbstractDomainEventDispatcher {

    protected DomainEventDispatcher(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Bean
    public IntegrationFlow domainEventFlow() {
        return this.domainEventFlow(DomainEventDispatcher::build);
    }

    private static AbstractLocalMessage<?> build(GenericMessage<?> message) {
        //指定的
        if (message.getPayload() instanceof UserDomainEvent<?>) {
            return new UserLocalMessage(message);
        }

        //默认的
        return new LocalMessage(message);
    }

}
