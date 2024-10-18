package com.iokays.common.domain.event.store;

import com.iokays.common.core.event.DomainEvent;

import java.util.Objects;

public record CreatedOrderEvent(String id) implements DomainEvent<CreatedOrderEvent> {

    @Override
    public boolean sameEventAs(CreatedOrderEvent other) {
        return Objects.equals(this.id, other.id);
    }

}
