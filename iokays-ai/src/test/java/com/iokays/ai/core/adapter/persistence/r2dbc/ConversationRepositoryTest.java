package com.iokays.ai.core.adapter.persistence.r2dbc;

import com.iokays.common.core.security.AuthenticatedUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ConversationRepositoryTest {

    @Resource
    private ConversationRepository conversationRepository;

    @Test
    void test() {
    }

    private AuthenticatedUser authenticatedUser() {
        return () -> "test";
    }

}