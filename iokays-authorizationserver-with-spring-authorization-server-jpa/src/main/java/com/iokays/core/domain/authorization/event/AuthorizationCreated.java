package com.iokays.core.domain.authorization.event;

import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.authorization.AuthorizationId;
import lombok.Builder;

import java.time.Instant;


@Builder
public record AuthorizationCreated(
        EventId id,
        AuthorizationId authorizationId,
        Instant createdAt
) implements DomainEvent<AuthorizationCreated> {
    @Override
    public boolean sameEventAs(AuthorizationCreated other) {
        return false;
    }
}
