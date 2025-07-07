package com.iokays.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.control.Try;

/**
 * 该Json对没有使用领域接口,抽象类使用的Json序列化,反序列化工具
 */
public class DomainEventInteropJsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
    }


    public static String toJson(Object x) {
        return Try.of(() -> objectMapper.writeValueAsString(x)).get();
    }

    public static byte[] toBytes(Object x) {
        return Try.of(() -> objectMapper.writeValueAsBytes(x)).get();
    }

    public static <T> T fromJson(byte[] bytes, Class<T> targetType) {
        return Try.of(() -> objectMapper.readValue(bytes, targetType)).get();
    }

    public static <T> T fromJson(String json, Class<T> targetType) {
        return Try.of(() -> objectMapper.readValue(json, targetType)).get();
    }

}