package com.iokays.core.adapter.listener;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.common.core.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@DrivenAdapter
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ)
class DomainEventBusMessageListener {

    @EventListener
    public void handle(final DomainEvent<?> evt) {
        //消息发布[入库], 分布式事务本地消息表的持久化的位置
        log.info("消息发布[入库]: 领域事件: {}", evt);
    }
}