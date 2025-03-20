package com.iokays.authorization.core.domain.clientregistration;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ClientRegistrationInfo(ClientRegistrationId clientRegistrationId,
                                     ClientRegistrationType clientRegistrationType,
                                     String clientId,
                                     String clientName,
                                     String clientSecret,
                                     String clientAuthenticationMethod,
                                     String authorizationGrantType,
                                     String redirectUri,
                                     Set<String> scopes,
                                     String authorizationUri,
                                     String tokenUri,
                                     String userInfoUri,
                                     String userNameAttributeName,
                                     String jwkSetUri,
                                     LocalDateTime createdDate,
                                     LocalDateTime lastModifiedDate
) {
}
