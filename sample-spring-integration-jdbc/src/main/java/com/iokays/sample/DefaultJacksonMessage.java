package com.iokays.sample;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.integration.support.MutableMessage;

import java.util.Map;

/**
 * 替代 Spring Integration 的 Message 默认序列化的实体类
 */
public class DefaultJacksonMessage extends MutableMessage<Object> {

    @JsonCreator
    public DefaultJacksonMessage(@JsonProperty("payload") final Object payload,
                                 @JsonProperty("headers") final Map<String, Object> headers) {
        super(payload, headers);
    }
}
