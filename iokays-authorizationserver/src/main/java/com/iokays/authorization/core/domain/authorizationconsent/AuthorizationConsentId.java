package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import com.iokays.common.domain.jpa.AbstractId;
import jakarta.persistence.Embeddable;

@Embeddable
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