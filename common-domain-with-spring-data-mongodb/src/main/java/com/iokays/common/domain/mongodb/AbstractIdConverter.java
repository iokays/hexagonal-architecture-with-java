package com.iokays.common.domain.mongodb;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class AbstractIdConverter implements Converter<AbstractId, String> {

    @Override
    public String convert(AbstractId source) {
        return source.id();
    }
}