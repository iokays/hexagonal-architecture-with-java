package com.iokays.authorization.core.domain.authorization.event;

import com.iokays.authorization.core.domain.authorization.AuthorizationCode;
import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import lombok.Builder;

import java.time.Instant;


@Builder
public record AuthorizationCreated(
        EventId eventId,
        AuthorizationCode authorizationId,
        Instant createdAt
) implements DomainEvent<AuthorizationCreated> {
    @Override
    public boolean sameEventAs(AuthorizationCreated other) {
        return false;
    }
}
