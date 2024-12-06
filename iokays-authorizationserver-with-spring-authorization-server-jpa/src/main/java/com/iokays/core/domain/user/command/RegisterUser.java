package com.iokays.core.domain.user.command;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import com.iokays.core.domain.user.Password;

/**
 * 注册新客户的不可变记录命令
 *
 * @param id {@link CommandId} 命令ID
 */
public record RegisterUser(CommandId id,
                           String username,
                           Password password) implements Command {
}
