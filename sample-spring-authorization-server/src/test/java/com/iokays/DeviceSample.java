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

@Slf4j
class DeviceSample {

    private final RestTemplate restTemplate = new RestTemplate();
    @Test
    @DisplayName("设备授权")
    void testDeviceAuthorization() {
        //请求头
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("response_type", "device_code");
        map.add("client_id", "login-client");
        map.add("scope", "profile");

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/device_authorization";

        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);
    }

    @Test
    void testToken() {
        //请求头
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 设置请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "login-client");
        map.add("device_code", "oUx3dhN-i9VCLGrdwAL91UEupenxQe1gAOi5PGOeUuWwWqJeGBeBK0v5caTgjWkfSBG1xOx7G21xvq8N2t_BSv_4NlYJGv_ZwrJlZtf7EbxrsQkS_q01MSTjdgqMWaHQ");
        map.add("grant_type", "urn:ietf:params:oauth:grant-type:device_code");

        final var requestEntity = new HttpEntity<>(map, headers);
        final String url = "http://localhost:8080/oauth2/token";

        final var response = restTemplate.postForObject(url, requestEntity, String.class);
        log.info("response: {}", response);

    }

}
