package com.iokays.core.application.service;

import com.iokays.core.domain.clientregistration.ClientRegistration;
import com.iokays.core.domain.clientregistration.ClientRegistrationId;
import com.iokays.core.domain.clientregistration.ClientRegistrationInfo;
import com.iokays.core.domain.clientregistration.ClientRegistrationRepository;
import com.iokays.core.domain.clientregistration.command.CreateClientRegistration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ClientRegistrationApplicationService {

    private final ClientRegistrationRepository clientRegistrationRepository;

    @NotNull
    @Transactional(readOnly = true)
    @Cacheable(value = "ClientRegistrationByAll")
    public List<ClientRegistrationInfo> findAll() {
        return clientRegistrationRepository.findAll().stream().map(ClientRegistration::info).toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "ClientRegistrationById", key = "#registrationId")
    public ClientRegistrationInfo findByRegistrationId(String registrationId) {
        log.info("registrationId: {}", registrationId);
        final var clientRegistration = Objects.requireNonNull(clientRegistrationRepository.findByClientRegistrationId(new ClientRegistrationId(registrationId)));
        return clientRegistration.info();
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "ClientRegistrationByAll"),
                    @CacheEvict(value = "ClientRegistrationById", key = "#result.id")
            })
    public ClientRegistrationId createClientRegistration(CreateClientRegistration command) {
        Validate.isTrue(!this.checkClientNameExist(command.clientName()), "ClientName: " + command.clientName() + " 已经存在");
        log.info("创建客户端:{}", command.clientName());
        return clientRegistrationRepository.save(new ClientRegistration(command)).clientRegistrationId();
    }

    private boolean checkClientNameExist(final String clientName) {
        return null != clientRegistrationRepository.findByClientName(clientName);
    }

}
