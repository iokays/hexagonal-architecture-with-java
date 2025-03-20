package com.iokays.authorization.core.adapter.persistence.jpa;

import com.iokays.authorization.core.domain.authorization.Authorization;
import com.iokays.authorization.core.domain.authorization.AuthorizationId;
import com.iokays.authorization.core.domain.authorization.AuthorizationRepository;
import com.iokays.common.core.adapter.DrivenAdapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@DrivenAdapter
public interface AuthorizationJpaRepository extends AuthorizationRepository, JpaRepository<Authorization, Long> {

    @Query("select a.authorizationId from Authorization a where a.state = :token" +
            " or a.authorizationCodeValue = :token" +
            " or a.accessTokenValue = :token" +
            " or a.refreshTokenValue = :token" +
            " or a.oidcIdTokenValue = :token" +
            " or a.userCodeValue = :token" +
            " or a.deviceCodeValue = :token"
    )
    Optional<AuthorizationId> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(@Param("token") String token);


}
