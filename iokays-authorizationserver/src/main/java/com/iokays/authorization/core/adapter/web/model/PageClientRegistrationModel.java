package com.iokays.authorization.core.adapter.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationType;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用于表示分页查询中的客户端注册信息的模型类。
 * 该类扩展了客户端注册的基本信息，并增加了客户端注册的唯一标识符（ClientRegistrationId）。
 * 通常用于在分页查询中返回客户端注册的详细信息。
 */
public record PageClientRegistrationModel(
        String registrationId, // 客户端注册的唯一标识符，用于唯一标识一个客户端注册
        ClientRegistrationType clientRegistrationType,
        String clientId, // 客户端的唯一标识符，用于在授权服务器中识别客户端
        String clientName, // 客户端的名称，用于展示或管理
        String clientSecret, // 客户端的密钥，用于客户端认证
        String clientAuthenticationMethod, // 客户端认证方法，例如"client_secret_basic"或"client_secret_post"
        String authorizationGrantType, // 授权授予类型，例如"authorization_code"或"client_credentials"
        String redirectUri, // 授权成功后的重定向URI，用于接收授权码
        Set<String> scopes, // 客户端请求的权限范围，例如"read"、"write"等
        String authorizationUri, // 授权服务器的授权端点URI，用于获取授权码
        String tokenUri, // 授权服务器的令牌端点URI，用于获取访问令牌
        String userInfoUri, // 用户信息端点URI，用于获取用户信息（OpenID Connect）
        String userNameAttributeName, // 用户信息中用于标识用户名的属性名称
        String jwkSetUri, // JSON Web Key Set (JWKS) URI，用于获取公钥以验证JWT签名
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") LocalDateTime createdDate, // 创建时间
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") LocalDateTime lastModifiedDate, // 最后更新时间
        Set<String> actions // 可执行的操作，例如"update"、"delete"等
) {
}
