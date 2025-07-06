package com.iokays.serialization.jackson.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iokays.common.core.event.EventId;

import java.io.IOException;

public class EventIdSerializer<T extends EventId> extends JsonSerializer<T> {

        @Override
        public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // 自定义序列化逻辑
            if (null != value) {
                gen.writeString(value.id());
            }
        }
    }