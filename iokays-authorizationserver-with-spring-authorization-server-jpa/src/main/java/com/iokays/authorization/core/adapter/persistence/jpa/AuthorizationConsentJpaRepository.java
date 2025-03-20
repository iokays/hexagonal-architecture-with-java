package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.authorizationconsent.AuthorizationConsent;
import com.iokays.authorization.core.domain.authorizationconsent.AuthorizationConsentRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DrivenAdapter
public interface AuthorizationConsentJpaRepository extends AuthorizationConsentRepository, JpaRepository<AuthorizationConsent, Long> {

}
