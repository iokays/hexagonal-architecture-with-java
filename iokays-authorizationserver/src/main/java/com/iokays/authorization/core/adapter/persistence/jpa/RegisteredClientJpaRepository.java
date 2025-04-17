package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClient;
import com.iokays.authorization.core.domain.registeredclient.RegisteredClientRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface RegisteredClientJpaRepository extends JpaRepository<RegisteredClient, Long>, RegisteredClientRepository {
}
