package com.iokays.core.domain.user.event;

import com.iokays.common.core.event.EventId;
import com.iokays.core.domain.user.UserId;

import java.time.LocalDateTime;

public record UserRegistered(EventId eventId,
                             UserId userId,
                             String username,
                             Boolean enabled,
                             LocalDateTime registeredAt) implements UserDomainEvent<UserRegistered> {
    @Override
    public boolean sameEventAs(UserRegistered other) {
        return this == other;
    }

}
