package com.iokays.dispatch.core.adapter.job.rest;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
class RestClientJobTest {

    @Resource
    private RestTemplate restTemplate;

    @Test
    public void test() {
        final var url = "https://www.iokays.com/api/users";
        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("response: {}", response);
    }

}