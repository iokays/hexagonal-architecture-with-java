package com.iokays.core.domain.clientregistration;

import java.util.Set;

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
                                     String jwkSetUri
) {
}
