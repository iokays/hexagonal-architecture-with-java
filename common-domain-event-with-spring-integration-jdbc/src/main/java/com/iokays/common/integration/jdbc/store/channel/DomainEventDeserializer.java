package com.iokays.common.integration.jdbc.store.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.iokays.serialization.jackson.DomainEventStoreJsonMapper;
import org.springframework.core.serializer.Deserializer;
import org.springframework.integration.support.MutableMessage;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public class DomainEventDeserializer implements Deserializer<Message<?>> {

    @Override
    public Message<?> deserialize(InputStream inputStream) throws IOException {
        final byte[] bytes = inputStream.readAllBytes();
        return DomainEventStoreJsonMapper.fromJson(bytes, DomainEventInputMessage.class);
    }

    protected static class DomainEventInputMessage extends MutableMessage<Object> {

        public DomainEventInputMessage(
                @JsonProperty("payload") Object payload,
                @JsonProperty("headers") Map<String, Object> headers) {
            super(payload, headers);
        }
    }
}
