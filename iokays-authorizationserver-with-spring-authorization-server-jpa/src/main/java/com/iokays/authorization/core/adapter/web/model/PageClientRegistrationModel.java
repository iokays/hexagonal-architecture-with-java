package com.iokays.authorization.core.adapter.web.model;

import com.iokays.authorization.core.domain.clientregistration.ClientRegistrationType;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * 用于表示分页查询中的客户端注册信息的模型类。
 * 该类扩展了客户端注册的基本信息，并增加了客户端注册的唯一标识符（ClientRegistrationId）。
 * 通常用于在分页查询中返回客户端注册的详细信息。
 */
public record PageClientRegistrationModel(
        // 客户端注册的唯一标识符，用于唯一标识一个客户端注册
        String clientRegistrationId,

        // 客户端注册类型，例如OAuth2或OpenID Connect
        ClientRegistrationType clientRegistrationType,

        // 客户端的唯一标识符，用于在授权服务器中识别客户端
        String clientId,

        // 客户端的名称，用于展示或管理
        String clientName,

        // 客户端的密钥，用于客户端认证
        String clientSecret,

        // 客户端认证方法，例如"client_secret_basic"或"client_secret_post"
        String clientAuthenticationMethod,

        // 授权授予类型，例如"authorization_code"或"client_credentials"
        String authorizationGrantType,

        // 授权成功后的重定向URI，用于接收授权码
        String redirectUri,

        // 客户端请求的权限范围，例如"read"、"write"等
        Set<String> scopes,

        // 授权服务器的授权端点URI，用于获取授权码
        String authorizationUri,

        // 授权服务器的令牌端点URI，用于获取访问令牌
        String tokenUri,

        // 用户信息端点URI，用于获取用户信息（OpenID Connect）
        String userInfoUri,

        // 用户信息中用于标识用户名的属性名称
        String userNameAttributeName,

        // JSON Web Key Set (JWKS) URI，用于获取公钥以验证JWT签名
        String jwkSetUri,

        // 创建时间
        LocalDateTime createdDate,

        // 最后更新时间
        LocalDateTime lastModifiedDate
) {
}
