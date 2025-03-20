package com.iokays.authorization.core.domain.registeredclient;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


@Builder
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
        String tokenSettings,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}