package com.iokays.authorization.core.domain.registeredclient.commond;

import com.iokays.common.core.command.Command;
import com.iokays.common.core.command.CommandId;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

/**
 * 客户端注册命令记录类。
 * 使用@Builder注解提供建造者模式支持，方便创建不可变对象。
 * 实现了Command接口，表示这是一个命令对象。
 */
@Builder
public record RegisterClient(
        CommandId id, // 命令的唯一标识符
        String clientId, // 客户端ID，用于唯一标识客户端
        Instant clientIdIssuedAt, // 客户端ID的颁发时间
        String clientSecret, // 客户端密钥，用于认证
        Instant clientSecretExpiresAt, // 客户端密钥的过期时间
        String clientName, // 客户端名称，可读性标识
        List<String> clientAuthenticationMethods, // 客户端认证方法列表，例如：client_secret_basic, client_secret_post等
        List<String> authorizationGrantTypes, // 授权许可类型列表，例如：authorization_code, refresh_token等
        List<String> redirectUris, // 重定向URI列表，用于授权码流程
        List<String> postLogoutRedirectUris, // 登出后重定向URI列表
        List<String> scopes, // 客户端请求的权限范围列表，例如：openid, profile, email等
        String clientSettings, // 客户端设置，通常为JSON格式字符串，包含客户端特定的配置选项
        String tokenSettings // 令牌设置，通常为JSON格式字符串，包含令牌相关的配置选项
) implements Command {
}
