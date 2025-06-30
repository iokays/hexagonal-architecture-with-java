package com.iokays.common.integration.jdbc.store.channel;

import org.springframework.core.serializer.Serializer;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;


public class DomainEventSerializer implements Serializer<Message<?>> {

    @Override
    public void serialize(Message<?> object, OutputStream outputStream) throws IOException {
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " +
                    "but received an object of type [" + object.getClass().getName() + "]");
        }
        final byte[] bytes = DomainEventStoreJsonMapper.toBytes(object);
        outputStream.write(bytes);
        outputStream.flush();
    }
}
