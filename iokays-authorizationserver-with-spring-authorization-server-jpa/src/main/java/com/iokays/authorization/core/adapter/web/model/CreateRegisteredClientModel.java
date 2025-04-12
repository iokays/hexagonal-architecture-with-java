package com.iokays.authorization.core.adapter.web.model;

import java.time.Instant;
import java.util.List;

/**
 * 已注册客户端模型记录类
 */
public record CreateRegisteredClientModel(
        String clientId, // 客户端唯一标识符
        Instant clientIdIssuedAt, // 客户端ID颁发时间
        String clientSecret, // 客户端密钥
        Instant clientSecretExpiresAt, // 客户端密钥过期时间(null表示永不过期)
        String clientName, // 客户端显示名称
        List<String> clientAuthenticationMethods, // 支持的认证方法列表
        List<String> authorizationGrantTypes, // 支持的授权类型列表
        List<String> redirectUris, // 有效重定向URI列表
        List<String> postLogoutRedirectUris, // 登出后重定向URI列表
        List<String> scopes, // 授权范围列表
        String clientSettings, // 客户端配置(JSON字符串)
        String tokenSettings // 令牌配置(JSON字符串)
) {
}