package com.iokays.core.domain.authorizationconsent;

import com.iokays.core.domain.registeredclient.RegisteredClientId;

import java.util.Optional;

public interface AuthorizationConsentRepository {

    AuthorizationConsent save(AuthorizationConsent entity);

    Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(RegisteredClientId registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(RegisteredClientId registeredClientId, String principalName);
}