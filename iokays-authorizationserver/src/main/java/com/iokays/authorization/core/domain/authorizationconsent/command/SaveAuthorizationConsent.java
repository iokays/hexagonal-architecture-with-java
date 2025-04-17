package com.iokays.authorization.core.domain.authorizationconsent.command;

import com.iokays.authorization.core.domain.registeredclient.RegisteredClientId;
import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

@Builder
public record SaveAuthorizationConsent(
        CommandId id,
        RegisteredClientId registeredClientId,
        String principalName,
        String authorities
) implements Command {
}
