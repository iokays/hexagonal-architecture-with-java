package com.iokays.common.domain.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.iokays.common.domain.jpa.AbstractId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class CustomerTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerTest.class);

    /**
     * 使用默认注解形式，对指定的属性字段 进行测试
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testAnnotation() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final var test1 = new ManyCustomerId();
        test1.customerId1 = new CustomerId(UUID.randomUUID().toString());
        test1.customerId2 = new CustomerId(UUID.randomUUID().toString());

        final var json = objectMapper.writeValueAsString(test1);
        log.info("test1_json: {}", json);
        Assertions.assertTrue(json.contains("\"customerId2\":{}")); //customerId2为空

        final var test2 = objectMapper.readValue(json, CustomerTest.class);
        log.info("test2_json: {}", objectMapper.writeValueAsString(test2));

    }

    /**
     * 序列化、反序列化都使用全局模式， 进行测试
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testSimpleModule() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        final SimpleModule simpleModule = new SimpleModule();
        //序列化
        simpleModule.addSerializer(AbstractId.class, new AbstractIdSerializer<>());

        //反序列化，需要指定为具体的实现类，不建议使用反射机制
        simpleModule.addDeserializer(CustomerId.class, new CustomerIdDeserializer());

        objectMapper.registerModule(simpleModule);

        final var test1 = new ManyCustomerId();
        log.info("test1_empty_json: {}", objectMapper.writeValueAsString(test1));

        test1.customerId1 = new CustomerId(UUID.randomUUID().toString());
        test1.customerId2 = new CustomerId(UUID.randomUUID().toString());

        final var json = objectMapper.writeValueAsString(test1);
        log.info("test1_json: {}", json);
        Assertions.assertFalse(json.contains("\"customerId2\":{}")); //customerId2不为空

        final var test2 = objectMapper.readValue(json, CustomerTest.class);
        log.info("test2_json: {}", objectMapper.writeValueAsString(test2));
    }

}
