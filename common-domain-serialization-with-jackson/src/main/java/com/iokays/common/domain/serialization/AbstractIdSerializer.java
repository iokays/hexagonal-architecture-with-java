package com.iokays.common.domain.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iokays.common.domain.jpa.AbstractId;

import java.io.IOException;

/**
 * 将默认的 abstractId.id() 序列化为Id字符串
 */
public class AbstractIdSerializer<T extends AbstractId> extends JsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // 自定义序列化逻辑
        if (null != value) {
            gen.writeString(value.id());
        }
    }
}