package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.common.domain.jpa.AbstractCode;
import jakarta.persistence.Embeddable;

@Embeddable
public class AuthorizationConsentCode extends AbstractCode {

    protected AuthorizationConsentCode() {
        super();
    }

    public AuthorizationConsentCode(String id) {
        super(id);
    }

    public static AuthorizationConsentCode makeAuthorizationConsentId(RegisteredClientCode registeredClientId, String principalName) {
        return new AuthorizationConsentCode(String.join("#", registeredClientId.code(), principalName));
    }

}