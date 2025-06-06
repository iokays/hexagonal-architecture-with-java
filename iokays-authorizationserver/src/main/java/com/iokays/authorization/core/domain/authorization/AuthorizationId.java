package com.iokays.authorization.core.domain.authorization;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class AuthorizationId extends AbstractId {

    protected AuthorizationId() {
        super();
    }

    public AuthorizationId(String id) {
        super(id);
    }

    public static AuthorizationId makeAuthorizationId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeAuthorizationId: {}", id);
        return new AuthorizationId(id);
    }
}
