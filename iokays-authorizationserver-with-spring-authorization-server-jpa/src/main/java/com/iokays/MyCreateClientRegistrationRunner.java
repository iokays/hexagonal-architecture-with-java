package com.iokays;

import com.iokays.authorization.core.application.service.ClientRegistrationApplicationService;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationType;
import com.iokays.authorization.core.domain.clientregistration.RegistrationId;
import com.iokays.authorization.core.domain.clientregistration.command.CreateClientRegistration;
import com.iokays.common.core.command.CommandId;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Order
@Component
@AllArgsConstructor
public class MyCreateClientRegistrationRunner implements CommandLineRunner {

    private final ClientRegistrationApplicationService clientRegistrationApplicationService;
    private final CustomProperties customProperties;

    @Override
    public void run(String... args) {
        {
            final var clientRegistration = CreateClientRegistration.builder()
                    .id(CommandId.generate())
                    .registrationId(new RegistrationId("GOOGLE"))
                    .clientRegistrationType(ClientRegistrationType.GOOGLE)
                    .clientId(customProperties.getGoogleClientId())
                    .clientName("Google")
                    .clientSecret(customProperties.getGoogleClientSecret())
                    .clientAuthenticationMethod("client_secret_basic")
                    .authorizationGrantType("authorization_code")
                    .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                    .scopes(Set.of("openid", "profile", "email", "address", "phone"))
                    .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                    .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                    .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                    .userNameAttributeName("sub")
                    .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                    .build();
            Try.run(() -> {
                final var clientRegistrationId = clientRegistrationApplicationService.createClientRegistration(clientRegistration);
                log.info("clientRegistrationId: {}", clientRegistrationId);
            });
        }

        final var clientRegistration = CreateClientRegistration.builder()
                .id(CommandId.generate())
                .clientRegistrationType(ClientRegistrationType.WORK_WEIXIN)
                .registrationId(new RegistrationId("WORK_WEIXIN"))
                .clientId(customProperties.getWorkWinXinClientId())
                .clientName("企微登录")
                .clientSecret(customProperties.getGoogleClientSecret())
                .clientAuthenticationMethod("client_secret_basic")
                .authorizationGrantType("authorization_code")
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scopes(Set.of("snsapi_privateinfo"))
                .authorizationUri("https://open.weixin.qq.com/connect/oauth2/authorize")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://qyapi.weixin.qq.com/cgi-bin/auth/getuserinfo")
                .userNameAttributeName("sub")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .build();

        Try.run(() -> {
            final var clientRegistrationId = clientRegistrationApplicationService.createClientRegistration(clientRegistration);
            log.info("clientRegistrationId: {}", clientRegistrationId);
        });

    }
}
