package com.iokays.authorization.core.domain.registeredclient;

import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class RegisteredClientId extends AbstractId {

    protected RegisteredClientId() {
        super();
    }

    public RegisteredClientId(String id) {
        super(id);
    }

    public static RegisteredClientId makeRegisteredClientId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeRegisteredClientId: {}", id);
        return new RegisteredClientId(id);
    }


}
