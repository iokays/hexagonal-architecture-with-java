package com.iokays.core.adapter.persistence.jpa;

import com.iokays.common.core.adapter.DrivenAdapter;
import com.iokays.core.domain.oauth2user.OauthUser;
import com.iokays.core.domain.oauth2user.OauthUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

@DrivenAdapter
public interface OauthUserJpaRepository extends JpaRepository<OauthUser, Long>, OauthUserRepository {
}
