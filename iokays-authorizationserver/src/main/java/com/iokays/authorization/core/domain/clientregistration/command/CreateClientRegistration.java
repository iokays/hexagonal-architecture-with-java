package com.iokays.authorization.core.domain.clientregistration.command;

import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationType;
import com.iokays.authorization.core.domain.clientregistration.RegistrationId;
import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateClientRegistration(CommandId id,
                                       RegistrationId registrationId,
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
) implements Command {
}
