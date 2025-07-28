package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import lombok.Builder;

@Builder
public record AuthorizationConsentInfo(
        AuthorizationConsentCode authorizationConsentId,
        RegisteredClientCode registeredClientId,
        String principalName,
        String authorities
) {
}