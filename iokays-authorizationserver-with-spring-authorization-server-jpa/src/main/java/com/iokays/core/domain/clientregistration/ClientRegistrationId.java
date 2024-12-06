package com.iokays.core.domain.clientregistration;

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

    public static ClientRegistrationId makeClientRegistrationId() {
        return new ClientRegistrationId(UUID.randomUUID().toString().replace("-", ""));
    }

}
