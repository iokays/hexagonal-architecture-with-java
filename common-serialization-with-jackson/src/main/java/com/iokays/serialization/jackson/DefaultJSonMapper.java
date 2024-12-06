package com.iokays.serialization.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Maps;
import io.vavr.control.Try;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static io.vavr.API.println;

public final class DefaultJSonMapper implements Serializable {


    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());
    }

    private DefaultJSonMapper() {
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

    public static void main(String[] args) throws IOException {
        final Map<String, Object> map = Maps.newHashMap();
        map.put("name", "sixOne");
        map.put("age", 18);
        map.put("birthday", null);
        final var bytes = objectMapper.writeValueAsBytes(map);
        println(StringUtils.toEncodedString(bytes, StandardCharsets.UTF_8));
    }

}