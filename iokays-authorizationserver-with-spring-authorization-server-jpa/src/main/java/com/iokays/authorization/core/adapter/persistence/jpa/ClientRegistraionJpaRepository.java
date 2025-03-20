package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.clientregistration.ClientRegistration;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface ClientRegistraionJpaRepository extends JpaRepository<ClientRegistration, Long>, ClientRegistrationRepository {
}
