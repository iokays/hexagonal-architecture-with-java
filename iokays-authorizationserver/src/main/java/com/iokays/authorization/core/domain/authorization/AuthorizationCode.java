package com.iokays.authorization.core.domain.authorization;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
@Embeddable
public class AuthorizationCode extends AbstractCode {

    protected AuthorizationCode() {
        super();
    }

    public AuthorizationCode(String code) {
        super(code);
    }

    public static AuthorizationCode makeAuthorizationCode() {
        String code = UUID.randomUUID().toString().replace("-", EMPTY);
        log.info("makeAuthorizationId: {}", code);
        return new AuthorizationCode(code);
    }
}
