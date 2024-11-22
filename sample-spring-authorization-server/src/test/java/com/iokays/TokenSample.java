package com.iokays;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class TokenSample {

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * <a href="http://localhost:8080/oauth2/token"/>
     * client_secret_basic, authorization_code
     */
    @Test
    @DisplayName("获取令牌")
    void testToken() {
        final var code = ConfigProperties.value("code");

        // 设置请求头 (因为client-authentication-method配置的是： client_secret_basic)
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("login-client:openid-connect").getBytes()));

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("code", code);
        map.add("redirect_uri", "https://www.iokays.com");
        map.add("scope", "openid");

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/token";
        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }

    @Test
    @DisplayName("刷新令牌, 和获取令牌的接口具有相同的返回结构")
    void testRefreshToken() {
        // 设置请求头 (因为client-authentication-method配置的是： client_secret_basic)
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("login-client:openid-connect").getBytes()));

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", ConfigProperties.value("refresh_token"));

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/token";
        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }

}
