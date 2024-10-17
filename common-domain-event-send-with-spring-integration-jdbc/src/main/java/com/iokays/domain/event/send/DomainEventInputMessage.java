package com.iokays.domain.event.send;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.integration.support.MutableMessage;

import java.util.Map;

public class DomainEventInputMessage extends MutableMessage<Object> {

    @JsonCreator
    public DomainEventInputMessage(@JsonProperty("payload") final Object payload,
                                   @JsonProperty("headers") final Map<String, Object> headers) {
        super(payload, headers);
    }
}
