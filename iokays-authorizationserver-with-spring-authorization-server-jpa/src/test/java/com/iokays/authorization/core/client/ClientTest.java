package com.iokays.authorization.core.client;

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

@Slf4j
public class ClientTest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @DisplayName("获取客户端令牌")
    void testToken() {
        // 设置请求头 (因为client-authentication-method配置的是： client_secret_basic)
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString(("login-client:secret").getBytes()));

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("scope", "read");

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8888/oauth2/token";
        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }
}
