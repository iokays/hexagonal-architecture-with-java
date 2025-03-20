package com.iokays.authorization.core.adapter.messaging.dispatcher;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.common.core.event.ApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.event.inbound.ApplicationEventListeningMessageProducer;
import org.springframework.stereotype.Component;

/**
 * 应用事件发布者，该发布不管业务的结果，只要有事件，就发布出去，且不管是否发布成功
 */
@Slf4j
@Component
@DrivenAdapter
class ApplicationEventDispatcher {

    @Bean
    public IntegrationFlow applicationEventFlow() {
        //将 ApplicationEvent 转换为 message
        final var eventsAdapter = new ApplicationEventListeningMessageProducer();
        eventsAdapter.setEventTypes(ApplicationEvent.class);

        return IntegrationFlow
                .from(eventsAdapter)
                .handle(v -> log.info("消息发布[直接发送到MQ, 不管成功与失败]. 应用事件: {}", v))
                .get();
    }
}
