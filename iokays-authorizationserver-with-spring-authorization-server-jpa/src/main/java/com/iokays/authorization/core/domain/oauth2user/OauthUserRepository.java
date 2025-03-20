package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationId;

import java.util.Optional;

public interface OauthUserRepository {

    OauthUser save(final OauthUser oauthUser);

    Optional<OauthUser> findByOauthUserId(OauthUserId oauthUserId);

    Optional<OauthUser> findBySubjectAndClientRegistrationId(String subject, ClientRegistrationId clientRegistrationId);

}
