package com.iokays;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * 这不是测试类(一个严格且具有断言，可重复执行测试用例的类)，只是使用@Test 来方便运行方法。
 */
@Slf4j
class TokenOtherSample {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @DisplayName("吊销令牌, 返回状态码200")
    void testRefreshToken() {
        // 设置请求头 (因为client-authentication-method配置的是： client_secret_basic)
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("login-client:openid-connect").getBytes()));

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", ConfigProperties.value("accessToken"));

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/revoke";
        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }

    /**
     * {
     * "active": true,                // Token 的活跃状态，true 表示该令牌是有效的
     * "sub": "user",                 // Subject（用户标识符），通常为用户的唯一标识
     * "aud": ["login-client"],       // Audience（受众），此令牌颁发的目标客户端（这里是 "login-client"）
     * "nbf": 1731390102,             // Not Before，UNIX 时间戳，表示该令牌在此时间之前无效
     * "scope": "openid",             // 权限范围，指令牌授予的权限（这里是 "openid"，用于获取用户身份信息）
     * "iss": "http://localhost:8080",// Issuer（发布者），签发该令牌的授权服务器地址
     * "exp": 1731390402,             // Expiration，UNIX 时间戳，指该令牌的过期时间
     * "iat": 1731390102,             // Issued At，UNIX 时间戳，指该令牌的签发时间
     * "jti": "f5718312-2d93-40fa-b51d-5ef157e028fd", // + JWT ID，用于唯一标识该令牌的标识符
     * "client_id": "login-client",   // 客户端 ID，表示令牌授权的客户端
     * "token_type": "Bearer"         // Token 类型，Bearer 表示该令牌为承载令牌，客户端需在请求头中携带它进行授权
     * }
     */
    @Test
    @DisplayName("检查访问令牌")
    void testIntrospectToken() {
        // 设置请求头 (因为client-authentication-method配置的是： client_secret_basic)
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("login-client:openid-connect").getBytes()));

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("token", ConfigProperties.value("accessToken"));

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/introspect";
        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }


}
