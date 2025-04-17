package com.iokays.authorization.core.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

@Slf4j
@SpringBootTest
class UserQueryAuthorizationApplicationServiceTest {

    @Resource
    private UserQueryApplicationService userQueryApplicationService;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    void testQuery() {
        Pageable.unpaged();
        final var page = userQueryApplicationService.page(Pageable.ofSize(10));
        Try.run(() -> log.info("result: {}", objectMapper.writeValueAsString(page)));
    }

}