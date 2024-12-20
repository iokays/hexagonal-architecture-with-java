package com.iokays.core.adapter.messaging.dispatcher;

import com.iokays.common.domain.localmessage.LocalMessage;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
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
     * 将LocalMessage 发送到 MQ.
     * @return
     */
    @Bean
    public IntegrationFlow domainEventSecondFlow() {
        return IntegrationFlow
                .from(Jpa.inboundAdapter(this.entityManagerFactory)
                                .entityClass(LocalMessage.class)
//                                .maxResults(1000) //如果指定了返回行，需要在查询指定排序的列
//                                .jpaQuery("FROM LocalMessage ORDER BY id")
                                .expectSingleResult(false)
                                .deleteAfterPoll(true)
                                .deleteInBatch(true)
                        ,
                        e -> e.poller(Pollers.fixedRate(1000).transactional()))
                .handle(v -> log.info("发送到MQ的消息: {}", v)) //排序后[不要依赖数据库的排序]，按顺序发送到 配置的 Spring Integration MQ.
                .get();
    }

}
