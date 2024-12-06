package com.iokays.core.adapter.listener;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.common.core.event.ApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 应用事件发布者，该发布不管业务的结果，只要有事件，就发布出去，且不管是否发布成功
 */
@Slf4j
@Component
@DrivenAdapter
class ApplicationEventBusMessageListener {

    @Async
    @EventListener
    public void handle(final ApplicationEvent<?> evt) {
        log.info("消息发布[入库]: 客户已注册事件: {}", evt);
    }
}
