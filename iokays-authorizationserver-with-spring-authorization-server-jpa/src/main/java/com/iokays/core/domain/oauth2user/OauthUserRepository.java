package com.iokays.core.domain.oauth2user;

import com.iokays.core.domain.clientregistration.ClientRegistrationId;

import java.util.Optional;

public interface OauthUserRepository {

    OauthUser save(final OauthUser oauthUser);

    Optional<OauthUser> findByOauthUserId(OauthUserId oauthUserId);

    Optional<OauthUser> findBySubjectAndClientRegistrationId(String subject, ClientRegistrationId clientRegistrationId);

}
