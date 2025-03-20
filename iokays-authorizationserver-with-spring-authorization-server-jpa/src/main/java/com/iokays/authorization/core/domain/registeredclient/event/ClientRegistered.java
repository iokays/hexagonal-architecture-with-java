package com.iokays.authorization.core.domain.registeredclient.event;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;

import java.time.Instant;

public record ClientRegistered(EventId eventId,
                               RegisteredClientId registeredClientId,
                               String clientId,
                               String clientName,
                               Instant registeredAt) implements DomainEvent<ClientRegistered> {
    @Override
    public boolean sameEventAs(ClientRegistered other) {
        return this == other;
    }

}
