package com.iokays.common.integration.jdbc.store.channel;

import org.springframework.core.serializer.Deserializer;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.io.InputStream;


public class DomainEventDeserializer implements Deserializer<Message<?>> {

    @Override
    public Message<?> deserialize(InputStream inputStream) throws IOException {
        final byte[] bytes = inputStream.readAllBytes();
        return DomainEventStoreJsonMapper.fromJson(bytes, DomainEventInputMessage.class);
    }
}
