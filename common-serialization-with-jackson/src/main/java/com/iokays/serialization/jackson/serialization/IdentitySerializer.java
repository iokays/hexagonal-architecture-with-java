package com.iokays.serialization.jackson.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iokays.common.core.domain.Identity;

import java.io.IOException;

/**
 * 将默认的 abstractId.id() 序列化为Id字符串
 * 当对象是 AbstractId, 将值序列化为: abstractId.id。而不是默认的嵌套结构。
 */
public class IdentitySerializer<T extends Identity> extends JsonSerializer<T> {

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // 自定义序列化逻辑
        if (null != value) {
            gen.writeString(value.id());
        }
    }
}