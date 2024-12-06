package com.iokays.core.domain.authorizationconsent;

import com.iokays.core.domain.registeredclient.RegisteredClientId;
import lombok.Builder;

@Builder
public record AuthorizationConsentInfo(
        AuthorizationConsentId authorizationConsentId,
        RegisteredClientId registeredClientId,
        String principalName,
        String authorities
) {
}