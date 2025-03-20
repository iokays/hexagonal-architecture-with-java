package com.iokays.authorization.core.adapter.web.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


public record PageRegisteredClientModel(
        String registeredClientId,
        String clientId,
        Instant clientIdIssuedAt,
        Instant clientSecretExpiresAt,
        String clientName,
        List<String> clientAuthenticationMethods,
        List<String> authorizationGrantTypes,
        List<String> redirectUris,
        List<String> postLogoutRedirectUris,
        List<String> scopes,
        String clientSettings,
        String tokenSettings,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}