package com.iokays.authorization.core.application.service;

import com.iokays.authorization.core.adapter.persistence.jpa.ClientRegistraionJpaRepository;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistration;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationInfo;
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
@AllArgsConstructor
@Transactional(readOnly = true)
public class ClientRegistrationQueryApplicationService implements ApplicationService {

    private final ClientRegistraionJpaRepository clientRegistraionJpaRepository;

    public Page<ClientRegistrationInfo> page(Pageable pageable) {
        final var page = clientRegistraionJpaRepository.findAll(pageable);
        return Pages.toNewPage(pageable, page, ClientRegistration::info);
    }

}
