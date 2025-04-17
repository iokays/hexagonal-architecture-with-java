package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.oauth2user.OauthUser;
import com.iokays.authorization.core.domain.oauth2user.OauthUserRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface OauthUserJpaRepository extends JpaRepository<OauthUser, Long>, OauthUserRepository {
}
