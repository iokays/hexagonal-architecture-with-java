package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;

import java.util.Optional;

public interface AuthorizationConsentRepository {

    AuthorizationConsent save(AuthorizationConsent entity);

    Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(RegisteredClientId registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(RegisteredClientId registeredClientId, String principalName);
}