package com.iokays.common.domain.event.store;

import com.iokays.common.core.event.EventId;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SampleApplicationService {

    private static final AtomicInteger atomicInteger = new AtomicInteger(10086);
    //logger
    private static final Logger log = LoggerFactory.getLogger(SampleApplicationService.class);

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public void runException() {
        log.info("start");
        applicationEventPublisher.publishEvent(new CreatedOrderEvent(EventId.form(Integer.toString(atomicInteger.getAndIncrement()))));
        log.info("end");
        throw new RuntimeException("测试事务回滚");
    }

    @Transactional
    public void run() {
        applicationEventPublisher.publishEvent(new CreatedOrderEvent(EventId.form(UUID.randomUUID().toString())));
        applicationEventPublisher.publishEvent(new CreatedOrderEvent(EventId.form(UUID.randomUUID().toString())));
        applicationEventPublisher.publishEvent(new CreatedOrderEvent(EventId.form(UUID.randomUUID().toString())));
    }

}
