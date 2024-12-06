package com.iokays.core.adapter.persistence.jpa;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.core.domain.authorization.AuthorizationRepository;
import com.iokays.core.domain.authorizationconsent.AuthorizationConsent;
import com.iokays.core.domain.authorizationconsent.AuthorizationConsentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface AuthorizationConsentJpaRepository extends AuthorizationConsentRepository, JpaRepository<AuthorizationConsent, Long> {

}
