package com.iokays.common.domain.event.store;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;

import java.util.Objects;

public record CreatedOrderEvent(EventId eventId) implements DomainEvent<CreatedOrderEvent> {

    @Override
    public boolean sameEventAs(CreatedOrderEvent other) {
        return Objects.equals(this.eventId, other.eventId);
    }

}
