package com.iokays.authorization.core.domain.authorizationconsent.command;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientCode;
import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

@Builder
public record SaveAuthorizationConsent(
        CommandId id,
        RegisteredClientCode registeredClientId,
        String principalName,
        String authorities
) implements Command {
}
