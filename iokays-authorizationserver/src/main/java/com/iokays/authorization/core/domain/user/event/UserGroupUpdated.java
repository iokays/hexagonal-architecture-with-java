package com.iokays.authorization.core.domain.user.event;

import com.iokays.common.core.event.EventId;

import java.time.LocalDateTime;
import java.util.List;

public record UserGroupUpdated(EventId eventId,
                               String username,
                               List<String> groupIds,
                               LocalDateTime registeredAt) implements UserDomainEvent<UserGroupUpdated> {
    @Override
    public boolean sameEventAs(UserGroupUpdated other) {
        return this == other;
    }

}
