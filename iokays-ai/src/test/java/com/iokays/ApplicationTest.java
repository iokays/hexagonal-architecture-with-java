package com.iokays;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApplicationTest {

    @Resource
    private ChatClient chatClient;

    @Test
    void test() {
        log.info("hello world");
        final String content = chatClient.prompt().user("Tell me a joke").call().content();
        log.info("content: {}", content);
    }

}