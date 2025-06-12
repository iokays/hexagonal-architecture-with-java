package com.iokays.authorization.core.adapter.web.model;

import com.iokays.authorization.core.domain.user.Password;
import lombok.Builder;

import java.util.List;

/**
 * 注册新客户的不可变记录命令
 *
 */
@Builder
public record SaveGroupModel(
        String groupName,
        List<String> authorities) {}
