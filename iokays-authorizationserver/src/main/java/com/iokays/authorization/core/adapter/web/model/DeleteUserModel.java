package com.iokays.authorization.core.adapter.web.model;

import lombok.Builder;

/**
 * 删除新客户的不可变记录命令
 */
@Builder
public record DeleteUserModel(String username) {
}
