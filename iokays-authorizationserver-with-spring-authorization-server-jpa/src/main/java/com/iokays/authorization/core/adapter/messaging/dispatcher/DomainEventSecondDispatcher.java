package com.iokays.authorization.core.adapter.messaging.dispatcher;

import com.iokays.authorization.core.domain.user.UserLocalMessage;
import com.iokays.common.domain.localmessage.LocalMessage;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jpa.dsl.Jpa;

/**
 * DomainEventSecondDispatcher
 * 将LocalMessage 发送到 MQ.
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class DomainEventSecondDispatcher {

    @Resource
    private final EntityManagerFactory entityManagerFactory;

    /**
     * 将默认的LocalMessage 发送到 MQ.
     *
     * @return
     */
//    @Bean
    public IntegrationFlow domainEventSecondFlow() {
        return IntegrationFlow
                .from(Jpa.inboundAdapter(this.entityManagerFactory)
                                .entityClass(LocalMessage.class)
                                .jpaQuery("FROM LocalMessage ORDER BY id")
                                .expectSingleResult(false)
                                .deleteAfterPoll(true)
                                .deleteInBatch(true)
                        ,
                        e -> e.poller(Pollers.fixedRate(10000).transactional()))
                .handle(v -> log.info("发送到MQ的消息: {}", v))
                .get();
    }

    /**
     * 非默认的localMessage 发送到 MQ.
     *
     * @return
     */
//    @Bean
    public IntegrationFlow userDomainEventSecondFlow() {
        return IntegrationFlow
                .from(Jpa.inboundAdapter(this.entityManagerFactory)
                                .entityClass(UserLocalMessage.class)
                                .jpaQuery("FROM UserLocalMessage ORDER BY id")
                                .expectSingleResult(false)
                                .deleteAfterPoll(true)
                                .deleteInBatch(true)
                        ,
                        e -> e.poller(Pollers.fixedRate(10000).transactional()))
                .handle(v -> log.info("发送到MQ的消息: {}", v))
                .get();
    }

}
