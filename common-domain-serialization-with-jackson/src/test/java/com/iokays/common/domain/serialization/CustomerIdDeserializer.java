package com.iokays.common.domain.serialization;

public class CustomerIdDeserializer extends AbstractIdDeserializer<CustomerId> {

    @Override
    protected CustomerId create(String text) {
        return new CustomerId(text);
    }

}
