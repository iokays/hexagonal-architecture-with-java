package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import lombok.Builder;

@Builder
public record AuthorizationConsentInfo(
        AuthorizationConsentId authorizationConsentId,
        RegisteredClientId registeredClientId,
        String principalName,
        String authorities
) {
}