package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.common.domain.jpa.AbstractId;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ClientRegistrationId extends AbstractId {

    protected ClientRegistrationId() {
        super();
    }

    public ClientRegistrationId(String id) {
        super(id);
    }

    public static ClientRegistrationId makeClientRegistrationId(final ClientRegistrationType type) {
        if (null != type) {
            return new ClientRegistrationId(type.name());
        }
        return new ClientRegistrationId(UUID.randomUUID().toString().replace("-", ""));
    }

}
