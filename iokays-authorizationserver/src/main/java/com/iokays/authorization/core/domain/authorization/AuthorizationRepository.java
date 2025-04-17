package com.iokays.authorization.core.domain.authorization;

import java.util.Optional;

public interface AuthorizationRepository {

    Authorization save(Authorization entity);

    Optional<Authorization> findByAuthorizationId(AuthorizationId authorizationId);

    Optional<Authorization> findByState(String state);

    Optional<Authorization> findByAuthorizationCodeValue(String authorizationCode);

    Optional<Authorization> findByAccessTokenValue(String accessToken);

    Optional<Authorization> findByRefreshTokenValue(String refreshToken);

    Optional<Authorization> findByOidcIdTokenValue(String idToken);

    Optional<Authorization> findByUserCodeValue(String userCode);

    Optional<Authorization> findByDeviceCodeValue(String deviceCode);

    Optional<AuthorizationId> findByStateOrAuthorizationCodeValueOrAccessTokenValueOrRefreshTokenValueOrOidcIdTokenValueOrUserCodeValueOrDeviceCodeValue(String token);

    void deleteByAuthorizationId(AuthorizationId authorizationId);


}
