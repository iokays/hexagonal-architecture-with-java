package com.iokays;

import com.iokays.common.core.command.CommandId;
import com.iokays.core.application.service.ClientRegistrationApplicationService;
import com.iokays.core.domain.clientregistration.ClientRegistrationType;
import com.iokays.core.domain.clientregistration.command.CreateClientRegistration;
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

    @Override
    public void run(String... args) {
        {
            final var clientRegistration = new CreateClientRegistration(
                    CommandId.generate(),
                    ClientRegistrationType.GOOGLE,
                    "google-client-id",
                    "Google",
                    "google-client-secret",
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
            clientRegistrationApplicationService.createClientRegistration(clientRegistration);
        }

        {
            final var clientRegistration = new CreateClientRegistration(
                    CommandId.generate(),
                    ClientRegistrationType.WORK_WEIXIN,
                    "xxxxxxxxxxxxxxx",
                    "企微登录",
                    "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx",
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
            clientRegistrationApplicationService.createClientRegistration(clientRegistration);
        }

    }
}