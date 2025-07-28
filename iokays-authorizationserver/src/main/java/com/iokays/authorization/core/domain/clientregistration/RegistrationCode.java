package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Embeddable
public class RegistrationCode extends AbstractCode {

    protected RegistrationCode() {
        super();
    }

    public RegistrationCode(String id) {
        super(id);
    }

    @Override
    protected void validateCode(String aCode) {
        super.validateCode(aCode);
    }
}
