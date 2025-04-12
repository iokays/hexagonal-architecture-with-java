package com.iokays.authorization.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


public record PageRegisteredClientModel(
        String registeredClientId, // 客户端注册的唯一标识符，用于唯一标识一个客户端注册
        String clientId, // 客户端的唯一标识符，用于在授权服务器中识别客户端
        Instant clientIdIssuedAt, // 客户端ID的颁发时间
        Instant clientSecretExpiresAt, // 客户端密钥的过期时间
        String clientName, // 客户端的名称，用于展示或管理
        List<String> clientAuthenticationMethods, // 客户端认证方法列表，例如：client_secret_basic, client_secret_post等
        List<String> authorizationGrantTypes, // 授权许可类型列表，例如：authorization_code, refresh_token等
        List<String> redirectUris,  // 重定向URI列表，用于授权码流程
        List<String> postLogoutRedirectUris, // 登出后重定向URI列表
        List<String> scopes, // 客户端请求的权限范围列表，例如：openid, profile, email等
        String clientSettings, // 客户端设置，通常为JSON格式字符串，包含客户端特定的配置选项
        String tokenSettings, // 令牌设置，通常为JSON格式字符串，包含令牌相关的配置选项
        @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime createdDate, // 创建时间
        @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8" )
        LocalDateTime lastModifiedDate, // 最后更新时间
        Set<String> actions // 可执行的操作，例如"update"、"delete"等
) {
}