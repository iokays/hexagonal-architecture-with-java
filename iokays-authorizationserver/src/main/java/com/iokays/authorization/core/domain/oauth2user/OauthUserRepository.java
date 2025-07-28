package com.iokays.authorization.core.domain.oauth2user;

import com.iokays.authorization.core.domain.clientregistration.RegistrationCode;

import java.util.Optional;

public interface OauthUserRepository {

    OauthUser save(final OauthUser oauthUser);

    Optional<OauthUser> findByOauthUserId(OauthUserCode oauthUserId);

    Optional<OauthUser> findBySubjectAndRegistrationId(String subject, RegistrationCode registrationId);

}
