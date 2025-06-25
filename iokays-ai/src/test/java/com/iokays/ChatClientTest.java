package com.iokays;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootTest
class ChatClientTest {

    @Resource
    private ChatClient chatClient;

    @Test
    void test() {
        log.info("hello world");
        final Flux<String> content = chatClient.prompt().user("你能做什么呢?").stream().content();
        log.info("content: {}", content.blockFirst());
    }

}