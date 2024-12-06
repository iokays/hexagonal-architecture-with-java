package com.iokays.core.domain.user.event;

import com.iokays.common.core.event.DomainEvent;

public interface UserDomainEvent<T extends UserDomainEvent> extends DomainEvent<T> {

}
