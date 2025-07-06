package com.iokays.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.iokays.common.core.domain.Identity;
import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.serialization.jackson.serialization.EventIdSerializer;
import com.iokays.serialization.jackson.serialization.IdentitySerializer;
import io.vavr.control.Try;

public class DomainEventStoreJsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        // 添加 领域接口等定制化的序列化方式
        final SimpleModule simpleModule = new SimpleModule();
        //特定序列化
        simpleModule.addSerializer(EventId.class, new EventIdSerializer<>());
        simpleModule.addSerializer(Identity.class, new IdentitySerializer<>());
        objectMapper.registerModule(simpleModule);

        //添加 @class，来让其他业务系统识别是什么领域事件
        final Class<?> springMessageClass = Try.of(() -> Class.forName("org.springframework.messaging.Message")).get();
        objectMapper.addMixIn(springMessageClass, SpringMessageRootObjectMixIn.class);
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

    interface SpringMessageRootObjectMixIn {

        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
        DomainEvent<?> getPayload();

    }


}