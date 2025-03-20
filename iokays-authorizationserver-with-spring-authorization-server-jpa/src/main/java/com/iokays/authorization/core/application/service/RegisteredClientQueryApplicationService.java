package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.adapter.persistence.jpa.RegisteredClientJpaRepository;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClient;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientInfo;
import com.iokays.authorization.core.utils.Pages;
import com.iokays.common.core.service.ApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class RegisteredClientQueryApplicationService implements ApplicationService {

    private final RegisteredClientJpaRepository registeredClientJpaRepository;

    public Page<RegisteredClientInfo> page(Pageable pageable) {
        final var page = registeredClientJpaRepository.findAll(pageable);
        return Pages.toNewPage(pageable, page, RegisteredClient::info);
    }

}
