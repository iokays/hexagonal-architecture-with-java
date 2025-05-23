package com.iokays.authorization.core.domain.user.command;

import com.iokays.authorization.core.domain.user.Password;
import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

import java.util.List;

/**
 * 注册新客户的不可变记录命令
 *
 * @param id {@link CommandId} 命令ID
 */
@Builder
public record RegisterUser(CommandId id,
                           String username,
                           Password password,
                           List<String> authorities
) implements Command {
}
