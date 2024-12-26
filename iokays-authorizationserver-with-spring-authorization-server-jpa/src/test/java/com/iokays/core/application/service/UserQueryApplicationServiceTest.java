package com.iokays.core.application.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserQueryApplicationServiceTest {

    @Resource
    private UserQueryApplicationService userQueryApplicationService;

    @Test
    void testQuery() {
        userQueryApplicationService.query();
    }

}