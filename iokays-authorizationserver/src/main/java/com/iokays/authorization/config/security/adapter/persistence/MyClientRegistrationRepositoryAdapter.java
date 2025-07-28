package com.iokays.authorization.config.security.adapter.persistence;

import com.google.common.collect.Sets;
import com.iokays.authorization.core.application.service.ClientRegistrationApplicationService;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class MyClientRegistrationRepositoryAdapter implements ClientRegistrationRepository {

    private ClientRegistrationApplicationService clientRegistrationApplicationService;

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        final var info = clientRegistrationApplicationService.findByRegistrationId(registrationId);
        return toClientRegistration(info);
    }

    private ClientRegistration toClientRegistration(ClientRegistrationInfo source) {
        log.debug("source: {}", source);
        return ClientRegistration
                .withRegistrationId(source.registrationId().code())
                .clientName(source.clientName())
                .clientId(source.clientId())
                .clientSecret(source.clientSecret())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(source.clientAuthenticationMethod()))
                .authorizationGrantType(new AuthorizationGrantType(source.authorizationGrantType()))
                .scope(source.scopes())
                .redirectUri(source.redirectUri())
                .authorizationUri(source.authorizationUri())
                .tokenUri(source.tokenUri())
                .jwkSetUri(source.jwkSetUri())
                .build();
    }

    public Map<String, String> loginUrlToClientName() {

        return new AbstractMap<>() {
            @NotNull
            @Override
            public Set<Entry<String, String>> entrySet() {
                final Set<Entry<String, String>> result = Sets.newHashSet();

                clientRegistrationApplicationService.findAll().forEach(v -> {
                    String authorizationRequestUri = "/oauth2/authorization/" + v.registrationId().code();
                    result.add(new SimpleEntry<>(authorizationRequestUri, v.clientName()));
                });
                return result;
            }
        };
    }

}
