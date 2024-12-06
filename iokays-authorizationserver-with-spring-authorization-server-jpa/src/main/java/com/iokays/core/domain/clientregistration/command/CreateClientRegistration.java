package com.iokays.core.domain.clientregistration.command;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import com.iokays.core.domain.clientregistration.ClientRegistrationType;
import lombok.Builder;

import java.util.Set;

@Builder
public record CreateClientRegistration(CommandId id,
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
