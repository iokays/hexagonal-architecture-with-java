package com.iokays.authorization.core.domain.clientregistration;

import com.iokays.common.core.infrastructure.Repository;

import java.util.List;

public interface ClientRegistrationRepository extends Repository {

    ClientRegistration findByClientName(String clientName);

    ClientRegistration findByClientRegistrationId(ClientRegistrationId clientRegistrationId);

    List<ClientRegistration> findAll();

    ClientRegistration save(ClientRegistration clientRegistration);

}
