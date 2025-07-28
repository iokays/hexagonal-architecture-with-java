package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.authorizationconsent.AuthorizationConsent;
import com.iokays.authorization.core.domain.authorizationconsent.AuthorizationConsentInfo;
import com.iokays.authorization.core.domain.authorizationconsent.AuthorizationConsentRepository;
import com.iokays.authorization.core.domain.authorizationconsent.command.SaveAuthorizationConsent;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class AuthorizationConsentApplicationService implements ApplicationService {

    private final AuthorizationConsentRepository authorizationConsentRepository;

    @CacheEvict(value = "AuthorizationConsentInfoByRegisteredClientIdAndPrincipalName", key = "#command.registeredClientId.id + ':' + #command.principalName")
    public void save(SaveAuthorizationConsent command) {
        log.debug("command: {}", command);
        final var original = authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(command.registeredClientId(), command.principalName());

        original.ifPresentOrElse(
                v -> v.updateAuthorities(command.authorities()),
                () -> authorizationConsentRepository.save(new AuthorizationConsent(command))
        );
    }

    @Cacheable(value = "AuthorizationConsentInfoByRegisteredClientIdAndPrincipalName", key = "#registeredClientId.id + ':' + #principalName")
    public AuthorizationConsentInfo findBy(RegisteredClientCode registeredClientId, String principalName) {
        return authorizationConsentRepository.findByRegisteredClientIdAndPrincipalName(registeredClientId, principalName)
                .map(AuthorizationConsent::info)
                .orElse(null);
    }

    @CacheEvict(value = "AuthorizationConsentInfoByRegisteredClientIdAndPrincipalName", key = "#registeredClientId.id + ':' + #principalName")
    public void deleteBy(RegisteredClientCode registeredClientId, String principalName) {
        authorizationConsentRepository.deleteByRegisteredClientIdAndPrincipalName(registeredClientId, principalName);
    }

}
