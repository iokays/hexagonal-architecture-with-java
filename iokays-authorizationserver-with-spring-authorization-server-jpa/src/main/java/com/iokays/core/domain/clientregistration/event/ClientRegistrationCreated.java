package com.iokays.core.domain.clientregistration.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;

import java.time.LocalDateTime;

public record ClientRegistrationCreated(EventId id,
                                        String clientName,
                                        LocalDateTime createdAt) implements DomainEvent<ClientRegistrationCreated> {
    @Override
    public boolean sameEventAs(ClientRegistrationCreated other) {
        return this.id.equals(other.id);
    }
}