package com.iokays.authorization.core.domain.registeredclient;

import com.iokays.common.core.infrastructure.Repository;

import java.util.Optional;


public interface RegisteredClientRepository extends Repository {

    RegisteredClient save(RegisteredClient registeredClient);

    RegisteredClient findByRegisteredClientId(RegisteredClientCode registeredClientId);

    Optional<RegisteredClient> findByClientId(String clientId);

    void delete(RegisteredClient registeredClient);

}