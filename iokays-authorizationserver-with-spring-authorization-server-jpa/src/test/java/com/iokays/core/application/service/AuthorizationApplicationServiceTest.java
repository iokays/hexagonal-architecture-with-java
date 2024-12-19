package com.iokays.core.application.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Slf4j
@SpringBootTest
class AuthorizationApplicationServiceTest {

    @Resource
    private AuthorizationApplicationService authorizationApplicationService;

    @Test
    void testFindAuthorizationIdByToken() {
        final var tokenValue = UUID.randomUUID().toString();
        log.info("tokenValue: {}", tokenValue);
        authorizationApplicationService.findAuthorizationIdByToken(tokenValue);
        log.info("tokenValue: {}", tokenValue);
        authorizationApplicationService.findAuthorizationIdByToken(tokenValue);
        log.info("tokenValue: {}", tokenValue);
        authorizationApplicationService.findAuthorizationIdByToken(tokenValue);
    }

}