package com.iokays.common.integration.jdbc.store.channel;

import com.iokays.serialization.jackson.DomainEventInteropJsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DomainEventInteropJsonMapperTest {


    @Test
    public void test() {
        final var json = "{\"payload\":{\"@class\":\"UserRegistered\",\"eventId\":\"44c7ab93-d4f1-4bc6-9f83-e4fad0ac8499\",\"username\":\"admin\",\"enabled\":true,\"registeredAt\":\"2025-07-05T23:34:14.185342\"},\"headers\":{\"id\":\"c9c16b3c-311f-014d-811f-df791de0129f\",\"timestamp\":1751729654227}}";

        final DomainEventDeserializer.DomainEventInputMessage message = DomainEventInteropJsonMapper.fromJson(json, DomainEventDeserializer.DomainEventInputMessage.class);

        log.info("message: {}", message);



    }

}