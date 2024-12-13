package com.iokays;

import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.RegisteredClientApplicationService;
import com.iokays.core.domain.registeredclient.commond.RegisterClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyCreateRegisteredClientRunner implements CommandLineRunner {

    private final RegisteredClientApplicationService registeredClientApplicationService;

    @Override
    public void run(String... args) {
        final var clientId = "login-client";

        if (null != registeredClientApplicationService.findByClientId(clientId)) {
            log.info("clientId: {}, 已存在", clientId);
            return;
        }

        log.info("创建 clientId: {}", clientId);

        final var command = RegisterClient.builder()
                .id(CommandId.generate())
                .clientName(clientId)
                .clientId(clientId)
                .clientSecret("{noop}openid-connect")
                .clientIdIssuedAt(Instant.now())
                .clientSecretExpiresAt(Instant.MAX)
                .clientAuthenticationMethods(List.of(
                        ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue(),
                        ClientAuthenticationMethod.NONE.getValue()))
                .authorizationGrantTypes(List.of(
                        AuthorizationGrantType.AUTHORIZATION_CODE.getValue(),
                        AuthorizationGrantType.DEVICE_CODE.getValue(),
                        AuthorizationGrantType.REFRESH_TOKEN.getValue()
                ))
                .redirectUris(List.of("https://www.iokays.com", "http://localhost:8082/login/oauth2/code/local"))
                .scopes(List.of(OidcScopes.OPENID, OidcScopes.PROFILE))
                .clientSettings(StringUtils.EMPTY)
                .tokenSettings(StringUtils.EMPTY)
                .build();

        registeredClientApplicationService.save(command);


    }
}
