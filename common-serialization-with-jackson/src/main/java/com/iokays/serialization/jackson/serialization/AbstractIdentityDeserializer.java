package com.iokays.serialization.jackson.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.iokays.common.core.domain.Identity;

import java.io.IOException;

/**
 * 反序列化，因为需要反序列化的子类，所以将该类设置为抽象类，让子类自己实现
 *
 * @param <T>
 */
public abstract class AbstractIdentityDeserializer<T extends Identity> extends JsonDeserializer<T> {

    @Override
    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return create(jsonParser.getText());
    }

    protected abstract T create(String text);

}