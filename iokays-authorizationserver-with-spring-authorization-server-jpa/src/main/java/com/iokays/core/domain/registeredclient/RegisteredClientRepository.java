package com.iokays.core.domain.registeredclient;

import com.iokays.common.core.infrastructure.Repository;

import java.util.Optional;


public interface RegisteredClientRepository extends Repository {

    RegisteredClient save(RegisteredClient registeredClient);

    RegisteredClient findByRegisteredClientId(RegisteredClientId registeredClientId);

    Optional<RegisteredClient> findByClientId(String clientId);

}