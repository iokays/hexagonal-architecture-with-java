package com.iokays.core.application.service;

import com.iokays.core.domain.authorizationconsent.AuthorizationConsent;
import com.iokays.core.domain.authorizationconsent.AuthorizationConsentInfo;
import com.iokays.core.domain.authorizationconsent.AuthorizationConsentRepository;
import com.iokays.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthorizationConsentApplicationService {

    private final AuthorizationConsentRepository authorizationConsentRepository;

    public void save(SaveAuthorizationConsent command) {
        log.debug("command: {}", command);
        final var original = authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(command.registeredClientId(), command.principalName());

        original.ifPresentOrElse(
                v -> v.updateAuthorities(command.authorities()),
                () -> authorizationConsentRepository.save(new AuthorizationConsent(command))
        );
    }

    public AuthorizationConsentInfo findBy(RegisteredClientId registeredClientId, String principalName) {
        return authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName)
                .map(AuthorizationConsent::info)
                .orElse(null);
    }

    public void deleteBy(RegisteredClientId registeredClientId, String principalName) {
        authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
    }

}
