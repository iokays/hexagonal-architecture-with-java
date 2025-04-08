package com.iokays.authorization.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
        @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime createdDate,
        @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime lastModifiedDate
) {
}