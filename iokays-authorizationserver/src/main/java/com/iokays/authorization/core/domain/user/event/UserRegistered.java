package com.iokays.authorization.core.domain.user.event;

import com.iokays.common.core.event.EventId;

import java.time.LocalDateTime;

public record UserRegistered(EventId eventId,
                             String username,
                             Boolean enabled,
                             LocalDateTime registeredAt) implements UserDomainEvent<UserRegistered> {
    @Override
    public boolean sameEventAs(UserRegistered other) {
        return this == other;
    }

}
