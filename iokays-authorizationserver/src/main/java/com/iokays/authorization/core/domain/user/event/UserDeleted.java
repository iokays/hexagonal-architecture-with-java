package com.iokays.authorization.core.domain.user.event;

import com.iokays.common.core.event.EventId;

import java.time.LocalDateTime;

public record UserDeleted(EventId eventId,
                          String username,
                          LocalDateTime registeredAt) implements UserDomainEvent<UserDeleted> {
    @Override
    public boolean sameEventAs(UserDeleted other) {
        return this == other;
    }

}
