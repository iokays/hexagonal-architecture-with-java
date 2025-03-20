package com.iokays;

import com.iokays.authorization.core.application.service.ClientRegistrationApplicationService;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationType;
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
            final var clientRegistration = new CreateClientRegistration(
                    CommandId.generate(),
                    ClientRegistrationType.GOOGLE,
                    customProperties.getGoogleClientId(),
                    "Google",
                    customProperties.getGoogleClientSecret(),
                    "client_secret_basic",
                    "authorization_code",
                    "{baseUrl}/login/oauth2/code/{registrationId}",
                    Set.of("openid", "profile", "email", "address", "phone"),
                    "https://accounts.google.com/o/oauth2/v2/auth",
                    "https://www.googleapis.com/oauth2/v4/token",
                    "https://www.googleapis.com/oauth2/v3/userinfo",
                    "sub",
                    "https://www.googleapis.com/oauth2/v3/certs"
            );
            Try.run(() -> {
                final var clientRegistrationId = clientRegistrationApplicationService.createClientRegistration(clientRegistration);
                log.info("clientRegistrationId: {}", clientRegistrationId);
            });
        }

        {
            final var clientRegistration = new CreateClientRegistration(
                    CommandId.generate(),
                    ClientRegistrationType.WORK_WEIXIN,
                    customProperties.getWorkWinXinClientId(),
                    "企微登录",
                    customProperties.getGoogleClientSecret(),
                    "client_secret_basic",
                    "authorization_code",
                    "{baseUrl}/login/oauth2/code/{registrationId}",
                    Set.of("snsapi_privateinfo"),
                    "https://open.weixin.qq.com/connect/oauth2/authorize",
                    "https://www.googleapis.com/oauth2/v4/token",
                    "https://qyapi.weixin.qq.com/cgi-bin/auth/getuserinfo",
                    "sub",
                    "https://www.googleapis.com/oauth2/v3/certs"
            );
            Try.run(() -> {
                final var clientRegistrationId = clientRegistrationApplicationService.createClientRegistration(clientRegistration);
                log.info("clientRegistrationId: {}", clientRegistrationId);
            });
        }

    }
}
