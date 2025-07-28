package com.iokays.common.domain.localmessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iokays.common.domain.jpa.AbstractCode;
import io.vavr.control.Try;

import java.io.IOException;

public abstract class LocalMessageMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());

        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(AbstractCode.class, new JsonSerializer<>() {
            @Override
            public void serialize(AbstractCode value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                // 自定义序列化逻辑
                if (null != value) {
                    gen.writeString(value.code());
                }
            }
        });
        objectMapper.registerModule(simpleModule);
    }

    private LocalMessageMapper() {
    }

    public static byte[] toBytes(Object x) {
        return Try.of(() -> objectMapper.writeValueAsBytes(x)).get();
    }

}