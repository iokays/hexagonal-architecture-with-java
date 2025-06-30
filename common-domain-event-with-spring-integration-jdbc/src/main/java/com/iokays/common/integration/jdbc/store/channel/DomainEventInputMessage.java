package com.iokays.common.integration.jdbc.store.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.integration.support.MutableMessage;

import java.util.Map;

public class DomainEventInputMessage extends MutableMessage<Object> {

    public DomainEventInputMessage(
            @JsonProperty("payload") Object payload,
            @JsonProperty("headers") Map<String, Object> headers) {
        super(payload, headers);
    }
}
