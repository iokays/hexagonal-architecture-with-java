package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClient;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientRepository;
import com.iokays.authorization.core.domain.registeredclient.commond.RegisterClient;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegisteredClientApplicationService implements ApplicationService {

    private final RegisteredClientRepository registeredClientRepository;

    @Caching(evict = {
            @CacheEvict(value = "ClientRegistrationByClientId", key = "#registerClient.clientId"),
            @CacheEvict(value = "ClientRegistrationById", key = "#result.id")
    })
    public RegisteredClientCode save(RegisterClient registerClient) {
        final RegisteredClient registeredClient = RegisteredClient.make(registerClient);
        registeredClientRepository.save(registeredClient);
        return registeredClient.registeredClientId();
    }

    @Cacheable(value = "ClientRegistrationById", key = "#id.id")
    public RegisteredClientInfo findById(final RegisteredClientCode id) {
        return registeredClientRepository.findByRegisteredClientId(id).info();
    }

    @Cacheable(value = "ClientRegistrationByClientId", key = "#clientId")
    public RegisteredClientInfo findByClientId(final String clientId) {
        return registeredClientRepository.findByClientId(clientId).map(RegisteredClient::info).orElse(null);
    }

    @Caching(evict = {
            @CacheEvict(value = "ClientRegistrationById", key = "#id.id")
    })
    public void delete(final RegisteredClientCode id) {
        final var entity = registeredClientRepository.findByRegisteredClientId(id);
        registeredClientRepository.delete(entity);
    }

}
