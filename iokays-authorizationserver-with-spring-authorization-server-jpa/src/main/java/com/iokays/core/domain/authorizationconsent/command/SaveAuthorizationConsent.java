package com.iokays.core.domain.authorizationconsent.command;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import com.iokays.core.domain.registeredclient.RegisteredClientId;
import lombok.Builder;

@Builder
public record SaveAuthorizationConsent(
        CommandId id,
        RegisteredClientId registeredClientId,
        String principalName,
        String authorities
) implements Command {
}
