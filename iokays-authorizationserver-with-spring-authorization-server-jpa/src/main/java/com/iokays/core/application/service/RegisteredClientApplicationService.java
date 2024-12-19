package com.iokays.core.application.service;

import com.iokays.core.domain.registeredclient.RegisteredClient;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import com.iokays.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.core.domain.registeredclient.RegisteredClientRepository;
import com.iokays.core.domain.registeredclient.commond.RegisterClient;
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
public class RegisteredClientApplicationService {

    private final RegisteredClientRepository registeredClientRepository;

    @Caching(evict = {
            @CacheEvict(value = "ClientRegistrationByClientId", key = "#registerClient.clientId"),
            @CacheEvict(value = "ClientRegistrationById", key = "#result.id")
    })
    public RegisteredClientId save(RegisterClient registerClient) {
        final RegisteredClient registeredClient = RegisteredClient.make(registerClient);
        registeredClientRepository.save(registeredClient);
        return registeredClient.registeredClientId();
    }

    @Cacheable(value = "ClientRegistrationById", key = "#id.id")
    public RegisteredClientInfo findById(final RegisteredClientId id) {
        return registeredClientRepository.findByRegisteredClientId(id).info();
    }

    @Cacheable(value = "ClientRegistrationByClientId", key = "#clientId")
    public RegisteredClientInfo findByClientId(final String clientId) {
        return registeredClientRepository.findByClientId(clientId).map(RegisteredClient::info).orElse(null);
    }

}
