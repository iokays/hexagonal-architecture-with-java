package com.iokays.core.adapter.web.model;

import java.time.Instant;
import java.util.List;

public record CreateRegisteredClientModel(
        String clientId,
        Instant clientIdIssuedAt,
        String clientSecret,
        Instant clientSecretExpiresAt,
        String clientName,
        List<String> clientAuthenticationMethods,
        List<String> authorizationGrantTypes,
        List<String> redirectUris,
        List<String> postLogoutRedirectUris,
        List<String> scopes,
        String clientSettings,
        String tokenSettings
) {
}
