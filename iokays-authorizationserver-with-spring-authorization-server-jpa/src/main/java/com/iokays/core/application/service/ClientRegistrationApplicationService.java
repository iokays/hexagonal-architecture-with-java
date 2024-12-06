package com.iokays.core.application.service;

import com.iokays.core.domain.clientregistration.ClientRegistration;
import com.iokays.core.domain.clientregistration.ClientRegistrationId;
import com.iokays.core.domain.clientregistration.ClientRegistrationInfo;
import com.iokays.core.domain.clientregistration.ClientRegistrationRepository;
import com.iokays.core.domain.clientregistration.command.CreateClientRegistration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
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
    public List<ClientRegistrationInfo> findAll() {
        return clientRegistrationRepository.findAll().stream().map(ClientRegistration::info).toList();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "ClientRegistrationById", key = "#id")
    public ClientRegistrationInfo findByRegistrationId(String registrationId) {
        final var clientRegistration = Objects.requireNonNull(clientRegistrationRepository.findByClientRegistrationId(new ClientRegistrationId(registrationId)));
        return clientRegistration.info();
    }

    public void createClientRegistration(CreateClientRegistration command) {
        if (this.checkClientNameExist(command.clientName())) {
            log.warn("ClientName:{}已经存在", command.clientName());
            return;
        }
        log.info("创建客户端:{}", command.clientName());
        clientRegistrationRepository.save(new ClientRegistration(command));
    }

    private boolean checkClientNameExist(final String clientName) {
        return null != clientRegistrationRepository.findByClientName(clientName);
    }

}
