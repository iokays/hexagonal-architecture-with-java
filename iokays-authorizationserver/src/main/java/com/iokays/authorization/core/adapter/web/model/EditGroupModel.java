package com.iokays.authorization.core.adapter.web.model;

import lombok.Builder;

import java.util.List;

/**
 * 注册新客户的不可变记录命令
 *
 */
@Builder
public record EditGroupModel(
        String groupId,
        List<String> authorities) {}
