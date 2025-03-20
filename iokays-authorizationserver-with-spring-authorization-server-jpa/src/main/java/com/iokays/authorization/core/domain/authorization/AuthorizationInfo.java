package com.iokays.authorization.core.domain.authorization;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthorizationInfo(
        AuthorizationId authorizationId,
        RegisteredClientId registeredClientId,
        String principalName,
        String authorizationGrantType,
        String authorizedScopes,
        String attributes,
        String state,
        String authorizationCodeValue,
        Instant authorizationCodeIssuedAt,
        Instant authorizationCodeExpiresAt,
        String authorizationCodeMetadata,
        String accessTokenValue,
        Instant accessTokenIssuedAt,
        Instant accessTokenExpiresAt,
        String accessTokenMetadata,
        String accessTokenType,
        String accessTokenScopes,
        String refreshTokenValue,
        Instant refreshTokenIssuedAt,
        Instant refreshTokenExpiresAt,
        String refreshTokenMetadata,
        String oidcIdTokenValue,
        Instant oidcIdTokenIssuedAt,
        Instant oidcIdTokenExpiresAt,
        String oidcIdTokenMetadata,
        String oidcIdTokenClaims,
        String userCodeValue,
        Instant userCodeIssuedAt,
        Instant userCodeExpiresAt,
        String userCodeMetadata,
        String deviceCodeValue,
        Instant deviceCodeIssuedAt,
        Instant deviceCodeExpiresAt,
        String deviceCodeMetadata
) {
}