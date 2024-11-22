package com.iokays;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * 这不是测试类(一个严格且具有断言，可重复执行测试用例的类)，只是使用@Test 来方便运行方法。
 */
@Slf4j
class UserInfoSample {

    private final RestTemplate restTemplate = new RestTemplate();

    @Test
    @DisplayName("访问用户信息")
    void testUserInfo() {
        final String accessToken = ConfigProperties.value("accessToken");
        final var headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        final String url = "http://localhost:8080/userinfo";
        final var response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        /**
         * <200 OK OK,
         * {"sub":"user-iokays","name":"First Last","given_name":"First","family_name":"Last","middle_name":"Middle",
         * "nickname":"User","preferred_username":"user-iokays","profile":"https://example.com/user-iokays",
         * "picture":"https://example.com/user-iokays.jpg","website":"https://example.com","email":"user-iokays@example.com",
         * "email_verified":true,"gender":"female","birthdate":"1970-01-01","zoneinfo":"Europe/Paris","locale":"en-US",
         * "phone_number":"+1 (604) 555-1234;ext=5678","phone_number_verified":false,"address":{"formatted":"Champ de Mars\n5 Av.
         * Anatole France\n75007 Paris\nFrance"},"updated_at":"1970-01-01T00:00:00Z"},[X-Content-Type-Options:"nosniff",
         * X-XSS-Protection:"0", Cache-Control:"no-cache, no-store, max-age=0, must-revalidate", Pragma:"no-cache", Expires:"0",
         * X-Frame-Options:"DENY", Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Thu, 14 Nov 2024 09:20:34 GMT",
         * Keep-Alive:"timeout=60", Connection:"keep-alive"]
         * >
         */
        log.info("response: {}", response);
    }
}
