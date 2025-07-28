package com.iokays.authorization.core.domain.registeredclient;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class RegisteredClientCode extends AbstractCode {

    protected RegisteredClientCode() {
        super();
    }

    public RegisteredClientCode(String id) {
        super(id);
    }

    public static RegisteredClientCode makeRegisteredClientId() {
        String id = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeRegisteredClientId: {}", id);
        return new RegisteredClientCode(id);
    }


}
