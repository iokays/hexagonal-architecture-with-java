package com.iokays.common.domain.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 这个对象有多个 CustomerId 属性，
 */
public class ManyCustomerId {

    /**
     * 使用注解指定了 Jackson 序列化与反序列化 CustomerId
     */
    @JsonSerialize(using = AbstractIdSerializer.class)
    @JsonDeserialize(using = CustomerIdDeserializer.class)
    protected CustomerId customerId1;

    /**
     * 使用注解 仅仅指定了 Jackson 反序列化 CustomerId
     */
    @JsonDeserialize(using = CustomerIdDeserializer.class)
    protected CustomerId customerId2;

    public CustomerId getCustomerId1() {
        return customerId1;
    }

    public CustomerId getCustomerId2() {
        return customerId2;
    }

}