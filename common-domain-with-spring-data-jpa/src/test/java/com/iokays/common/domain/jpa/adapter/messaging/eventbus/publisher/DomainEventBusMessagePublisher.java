package com.iokays.common.domain.jpa.adapter.messaging.eventbus.publisher;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.common.core.event.DomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.println;

@Component
@DrivenAdapter
@Transactional(propagation = Propagation.MANDATORY, isolation = Isolation.REPEATABLE_READ)
class DomainEventBusMessagePublisher {

    @EventListener
    public void handle(final DomainEvent<?> evt) {
        //消息发布[入库], 分布式事务本地消息表的持久化的位置
        println(STR."消息发布[入库]: 客户已注册事件: \{evt}");
    }
}
