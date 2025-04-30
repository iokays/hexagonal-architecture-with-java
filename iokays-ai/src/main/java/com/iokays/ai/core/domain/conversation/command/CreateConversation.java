
package com.iokays.ai.core.domain.conversation.command;

import com.iokays.common.core.security.AuthenticatedCommand;
import com.iokays.common.core.command.CommandId;
import com.iokays.common.core.security.AuthenticatedUser;
import lombok.Builder;

@Builder
public record CreateConversation (
        CommandId id,
        AuthenticatedUser authenticatedUser,
        String act,
        String prompt
) implements AuthenticatedCommand { }