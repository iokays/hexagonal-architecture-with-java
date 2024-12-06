package com.iokays.core.adapter.persistence.jpa;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.core.domain.clientregistration.ClientRegistration;
import com.iokays.core.domain.clientregistration.ClientRegistrationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface ClientRegistraionJpaRepository extends JpaRepository<ClientRegistration, Long>, ClientRegistrationRepository {
}
