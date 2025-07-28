package com.iokays.authorization.core.domain.authorizationconsent;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;

import java.util.Optional;

public interface AuthorizationConsentRepository {

    AuthorizationConsent save(AuthorizationConsent entity);

    Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(RegisteredClientCode registeredClientId, String principalName);

    void deleteByRegisteredClientIdAndPrincipalName(RegisteredClientCode registeredClientId, String principalName);
}