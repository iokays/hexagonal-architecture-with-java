package com.iokays.core.domain.registeredclient;

import java.time.Instant;
import java.util.List;


public record RegisteredClientInfo(
        RegisteredClientId registeredClientId,
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
        String tokenSettings) {
}