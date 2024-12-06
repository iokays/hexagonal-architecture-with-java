package com.iokays.sample;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.vavr.control.Try;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * DefaultJacksonMessage 序列化工具
 */
public class DefaultJacksonMessageMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 将对象序列化为JSON字节流 [序列化]
     *
     * @param object
     * @param outputStream
     */
    public static void toJson(Object object, OutputStream outputStream) {
        Try.run(() -> objectMapper.writeValue(outputStream, object));
    }

    /**
     * 将JSON字节流转换为对象 [反序列化]
     *
     * @param inputStream
     * @return
     */
    public static DefaultJacksonMessage fromJsonStream(InputStream inputStream) {
        return Try.of(() -> objectMapper.readValue(inputStream, DefaultJacksonMessage.class)).get();
    }
}
