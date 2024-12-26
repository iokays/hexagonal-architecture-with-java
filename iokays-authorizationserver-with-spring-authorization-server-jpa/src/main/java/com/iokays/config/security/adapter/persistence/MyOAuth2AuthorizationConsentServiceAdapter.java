package com.iokays.config.security.adapter.persistence;

import com.iokays.core.application.service.AuthorizationConsentApplicationService;
import com.iokays.core.application.service.RegisteredClientApplicationService;
import com.iokays.core.domain.authorizationconsent.AuthorizationConsentInfo;
import com.iokays.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsent;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class MyOAuth2AuthorizationConsentServiceAdapter implements OAuth2AuthorizationConsentService {

    private final AuthorizationConsentApplicationService authorizationConsentApplicationService;
    private final RegisteredClientApplicationService registeredClientApplicationService;

    @Override
    public void save(OAuth2AuthorizationConsent authorizationConsent) {
        final var command = toCommand(authorizationConsent);
        this.authorizationConsentApplicationService.save(command);
    }

    @Override
    public void remove(OAuth2AuthorizationConsent authorizationConsent) {
        final var registeredClientId = new RegisteredClientId(authorizationConsent.getRegisteredClientId());
        final var principalName = authorizationConsent.getPrincipalName();
        Validate.notNull(registeredClientId, "registeredClientId cannot be empty");
        Validate.notEmpty(principalName, "principalName cannot be empty");

        this.authorizationConsentApplicationService.deleteBy(registeredClientId, principalName);
    }

    @Override
    public OAuth2AuthorizationConsent findById(String registeredClientId, String principalName) {
        Validate.notEmpty(registeredClientId, "registeredClientId cannot be empty");
        Validate.notEmpty(principalName, "principalName cannot be empty");
        final var info = this.authorizationConsentApplicationService.findBy(new RegisteredClientId(registeredClientId), principalName);
        return info == null ? null : toObject(info);
    }

    private SaveAuthorizationConsent toCommand(OAuth2AuthorizationConsent authorizationConsent) {
        return SaveAuthorizationConsent.builder()
                .registeredClientId(new RegisteredClientId(authorizationConsent.getRegisteredClientId()))
                .principalName(authorizationConsent.getPrincipalName())
                .authorities(CollectionUtils.emptyIfNull(authorizationConsent.getAuthorities()).stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .build();
    }

    private OAuth2AuthorizationConsent toObject(AuthorizationConsentInfo authorizationConsent) {
        final var registeredClientId = authorizationConsent.registeredClientId();
        final var registeredClient = this.registeredClientApplicationService.findById(registeredClientId);
        if (registeredClient == null) {
            throw new DataRetrievalFailureException(
                    "The RegisteredClient with id '" + registeredClientId + "' was not found in the RegisteredClientRepository.");
        }

        final var builder = OAuth2AuthorizationConsent.withId(registeredClientId.id(), authorizationConsent.principalName());

        Arrays.stream(StringUtils.defaultString(authorizationConsent.authorities()).split(","))
                .forEach(v -> builder.authority(new SimpleGrantedAuthority(v)));

        return builder.build();
    }

}
