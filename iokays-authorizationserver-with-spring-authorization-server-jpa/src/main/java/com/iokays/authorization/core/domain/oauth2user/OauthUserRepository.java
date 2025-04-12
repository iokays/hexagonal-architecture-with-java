package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.authorization.core.domain.clientregistration.RegistrationId;

import java.util.Optional;

public interface OauthUserRepository {

    OauthUser save(final OauthUser oauthUser);

    Optional<OauthUser> findByOauthUserId(OauthUserId oauthUserId);

    Optional<OauthUser> findBySubjectAndRegistrationId(String subject, RegistrationId registrationId);

}
