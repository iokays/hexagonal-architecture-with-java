package com.iokays.core.domain.registeredclient.commond;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record RegisterClient(
        CommandId id,
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
        String tokenSettings) implements Command {
}
