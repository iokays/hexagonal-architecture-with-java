package com.iokays.core.adapter.persistence.jpa;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.core.domain.registeredclient.RegisteredClient;
import com.iokays.core.domain.registeredclient.RegisteredClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface RegisteredClientJpaRepository extends JpaRepository<RegisteredClient, Long>, RegisteredClientRepository {
}
