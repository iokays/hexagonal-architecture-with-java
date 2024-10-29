package com.iokays.common.domain.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * ObjectMapper 测试类
 */
class ObjectMapperTest {

    @Test
    void testSample() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();
        final var person = new Person("Andy", 30);

        //序列化
        final var json = objectMapper.writeValueAsString(person);
        Assertions.assertEquals("{\"name\":\"Andy\",\"age\":30}", json);

        //反序列化
        final var person2 = objectMapper.readValue(json, Person.class);
        Assertions.assertEquals(person, person2);
    }

    /**
     * ALWAYS：
     * 默认值。所有属性都会被包含在 JSON 中，无论它们的值是什么。
     * NON_NULL：
     * 只有非 null 的属性会被包含在 JSON 中。如果某个属性的值为 null，则该属性不会出现在生成的 JSON 字符串中。
     * NON_ABSENT：
     * 只有非 null 且非 Optional.empty() 的属性会被包含在 JSON 中。适用于 Java 8 的 Optional 类型。
     * NON_DEFAULT：
     * 只有非默认值的属性会被包含在 JSON 中。对于基本类型，这意味着只有非零值或非默认值的属性会被包含；对于对象类型，这意味着只有非 null 的属性会被包含。
     * NON_EMPTY：
     * 只有非 null 且非“空”的属性会被包含在 JSON 中。对于集合、数组和 Map，“空”意味着长度为 0；对于字符串，“空”意味着长度为 0 或仅包含空白字符。
     * CUSTOM：
     * 允许自定义包含逻辑。可以通过实现 JsonInclude.Value 接口来自定义包含规则
     *
     * @throws JsonProcessingException
     */
    @Test
    void testJsonInclude() throws JsonProcessingException {
        final var objectMapper = new ObjectMapper();

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //不含值null的字段
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY); //不含空字符串的字段

        final var person = new Person("", null);
        //序列化
        final var json = objectMapper.writeValueAsString(person);
        Assertions.assertEquals("{}", json);
    }

    /**
     * 测试对象
     */
    record Person(String name, Integer age) {
    }


}
