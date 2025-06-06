package com.iokays.authorization.core.domain.authorization.event;

import com.iokays.authorization.core.domain.authorization.AuthorizationId;
import com.iokays.common.core.event.DomainEvent;
import com.iokays.common.core.event.EventId;
import lombok.Builder;

import java.time.Instant;


@Builder
public record AuthorizationUpdated(
        EventId eventId,
        AuthorizationId authorizationId,
        Instant createdAt
) implements DomainEvent<AuthorizationUpdated> {
    @Override
    public boolean sameEventAs(AuthorizationUpdated other) {
        return false;
    }
}
