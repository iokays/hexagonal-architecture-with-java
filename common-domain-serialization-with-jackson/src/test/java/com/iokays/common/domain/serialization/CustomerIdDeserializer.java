package com.iokays.common.domain.serialization;

/**
 * CustomerId 反序列化子类。
 */
public class CustomerIdDeserializer extends AbstractIdDeserializer<CustomerId> {

    @Override
    protected CustomerId create(String text) {
        return new CustomerId(text);
    }

}
