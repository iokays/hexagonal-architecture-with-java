package com.iokays.core.domain.registeredclient.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.registeredclient.RegisteredClientId;

import java.time.Instant;

public record ClientRegistered(EventId id,
                               RegisteredClientId registeredClientId,
                               String clientId,
                               String clientName,
                               Instant registeredAt) implements DomainEvent<ClientRegistered> {
    @Override
    public boolean sameEventAs(ClientRegistered other) {
        return this == other;
    }

}
