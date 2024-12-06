package com.iokays.config.adapter.server;

import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.RegisteredClientApplicationService;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import com.iokays.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.core.domain.registeredclient.commond.RegisterClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@AllArgsConstructor
public class MyRegisteredClientRepositoryAdapter implements RegisteredClientRepository {

    private final RegisteredClientApplicationService registeredClientApplicationService;

    private static RegisterClient build(RegisteredClient client) {

        return RegisterClient.builder()
                .id(CommandId.generate())
                .clientId(client.getClientId())
                .clientIdIssuedAt(client.getClientIdIssuedAt())
                .clientSecret(client.getClientSecret())
                .clientSecretExpiresAt(client.getClientSecretExpiresAt())
                .clientName(client.getClientName())
                .clientAuthenticationMethods(client.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).toList())
                .authorizationGrantTypes(client.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).toList())
                .redirectUris(client.getRedirectUris().stream().toList())
                .postLogoutRedirectUris(client.getPostLogoutRedirectUris().stream().toList())
                .scopes(client.getScopes().stream().toList())
                .build();
    }

    protected static RegisteredClient build(RegisteredClientInfo client) {

        final var result = RegisteredClient.withId(client.registeredClientId().id())
                .clientId(client.clientId())
                .clientSecret(client.clientSecret())
                .clientAuthenticationMethods(v -> v.addAll(client.clientAuthenticationMethods().stream().map(ClientAuthenticationMethod::new).toList()))
                .authorizationGrantTypes(v -> v.addAll(client.authorizationGrantTypes().stream().map(AuthorizationGrantType::new).toList()))
                .redirectUris(v -> v.addAll(client.redirectUris()))
                .scopes(v -> v.addAll(client.scopes()))

                // 是否需要用户授权,当只需要openid scope时,不会出现授权页面 TODO
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(7)).build())

                .build();

        log.info("RegisteredClient: {}", result);

        return result;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        registeredClientApplicationService.save(build(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        final var info = registeredClientApplicationService.findById(new RegisteredClientId(id));
        return build(info);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        final var info = registeredClientApplicationService.findByClientId(clientId);
        return build(info);
    }
}
