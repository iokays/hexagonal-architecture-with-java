package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.common.domain.jpa.AbstractId;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistrationId extends AbstractId {

    protected RegistrationId() {
        super();
    }

    public RegistrationId(String id) {
        super(id);
    }

    @Override
    protected void validateId(String anId) {
        super.validateId(anId);
    }
}
