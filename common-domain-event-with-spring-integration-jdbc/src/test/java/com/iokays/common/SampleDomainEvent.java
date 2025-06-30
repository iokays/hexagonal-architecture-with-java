package com.iokays.common;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;

public record SampleDomainEvent(EventId eventId) implements DomainEvent<SampleDomainEvent> {
}
