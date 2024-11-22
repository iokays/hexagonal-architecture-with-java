package com.iokays.common.domain.mongodb;

import org.springframework.data.convert.PropertyValueConverter;
import org.springframework.data.mongodb.core.convert.MongoConversionContext;

public abstract class AbstractIdPropertyValueConverter<T extends AbstractId> implements PropertyValueConverter<T, String, MongoConversionContext> {
    @Override
    public final String write(T value, MongoConversionContext context) {
        return value.id();
    }

    @Override
    public final T read(String value, MongoConversionContext context) {
        return create(value);
    }

    protected abstract T create(String id);

}
