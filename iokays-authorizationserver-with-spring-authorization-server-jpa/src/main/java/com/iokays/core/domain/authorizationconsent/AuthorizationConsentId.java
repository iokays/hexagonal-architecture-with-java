package com.iokays.core.domain.authorizationconsent;

import com.iokays.common.domain.jpa.AbstractId;
import com.iokays.core.domain.registeredclient.RegisteredClientId;

public class AuthorizationConsentId extends AbstractId {

    protected AuthorizationConsentId() {
        super();
    }

    public AuthorizationConsentId(String id) {
        super(id);
    }

    public static AuthorizationConsentId makeAuthorizationConsentId(RegisteredClientId registeredClientId, String principalName) {
        return new AuthorizationConsentId(String.join("#", registeredClientId.id(), principalName));
    }

}