package com.iokays.authorization.core.domain.user;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Embeddable
public class Username extends AbstractId {

    protected Username() {
        super();
    }

    public Username(String id) {
        super(id);
    }

    public static Username of(final String name) {
        return new Username(name);
    }

}
